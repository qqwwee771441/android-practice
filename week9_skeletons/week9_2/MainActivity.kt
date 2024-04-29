class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateint var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val seoul = LatLng(37.521814, 126.923596)
        map.addMarker(MarkerOptions().position(seoul).title("Marker in Seoul"))
        map.moveCamera(CameraUpdateFactory.newLatLng(seoul))
    }
}