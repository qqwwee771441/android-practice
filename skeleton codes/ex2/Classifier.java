public class Classifier {
    private static final String MODEL_NAME = "keras_model_cnn.tflite";

    Context context;
    Interpreter interpreter = null;
    int modelInputWidth, modelInputHeight, modelInputChannel;
    int modelOutputClasses;

    public Classifier(Context context) {
        this.context = context;
    }

    public void init() throws IOException {
        ByteBuffer model = loadModelFile(MODEL_NAME);
        model.order(ByteOrder.nativeOrder());
        interpreter = new Interpreter(model);

        initModelShape();
    }

    private ByteBuffer loadModelFile(String modelName) throws IOException {
        AssetManager am = context.getAssets();
        AssetFileDescriptor afd = am.openFd(modelName);
        FileInputStream fis = new FileInputStream(afd.getFileDescriptor());
        FileChannel fc = fis.getChannel();
        long startOffset = afd.getStartOffset();
        long declaredLength = afd.getDeclaredLength();

        return fc.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private void initModelShape() {
        Tensor inputTensor = interpreter.getInputTensor(0);
        int[] inputShape = inputTensor.shape();
        modelInputChannel = inputShape[0];
        modelInputWidth = inputShape[1];
        modelInputHeight = inputShape[2];

        Tensor outputTensor = interpreter.getOutputTensor(0);
        int[] outputShape = outputTensor.shape();
        modelOutputClasses = outputShape[1];
    }

    private Bitmap resizeBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap, modelInputWidth, modelInputHeight, false);
    }

    private ByteBuffer convertBitmapToGrayByByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bitmap.getByteCount());
        byteBuffer.order(ByteOrder.nativeOrder());

        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        for (int pixel : pixels) {
            int r = pixel >> 16 & 0xFF;
            int g = pixel >> 8 & 0xFF;
            int b = pixel & 0xFF;

            float avgPixelValue = (r + g + b) / 3.0f;
            float normalizedPixelValue = avgPixelValue / 255.0f;

            byteBuffer.putFloat(normalizedPixelValue);
        }

        return byteBuffer;
    }

    public Pair<Integer, Float> classify(Bitmap image) {
        ByteBuffer buffer = convertBitmapToGrayByByteBuffer(resizeBitmap(image));

        float[][] result = new float[1][modelOutputClasses];

        interpreter.run(buffer, result);

        return argmax(result[0]);
    }

    private Pair<Integer, Float> argmax(float[] array) {
        int argmax = 0;
        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            float f = array[i];
            if (f > max) {
                argmax = i;
                max = f;
            }
        }
        return new Pair<>(argmax, max);
    }

    public void finish() {
        if (interpreter != null)
            interpreter.close();
    }
}

