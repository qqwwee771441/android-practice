import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.example.week55.Onefragment
import com.example.week55.R

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_main, container, false)

        // TODO: fragment_main.xml에 있는 버튼을 가져와서 fragButton에 저장
        val fragButton = findViewById<Button>(R.id.frag_btn)

        val fragmentManager = requireActivity().supportFragmentManager
        var onClick = false
        fragButton.setOnClickListener {
            if (onClick) {
                onClick = false
                val transaction = fragmentManager.beginTransaction()
                // TODO: fragmentManager를 사용하여 fragment를 제거
                val frameLayout = fragmentManager.findFragmentById(R.id.fragment_content)
                transaction.remove(frameLayout!!).commit()
            } else {
                onClick = true
                // TODO: fragmentManager를 사용하여 fragment를 추가
                val transaction : FragmentTransaction = fragmentManager.beginTransaction()
                transaction.add(R.id.fragment_content, Onefragment()).commit()
            }
        }
        return view
    }
}