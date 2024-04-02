package com.choi.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class OneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO : inflater.inflate를 사용하여 fragment_one.xml을 뷰로 반환
        return inflater.inflate(R.layout.fragment_bind, container, false)
    }
}
