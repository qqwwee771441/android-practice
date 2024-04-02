package com.choi.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class MyPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 1000       //Viewpager 옆으로 넘기는 페이지 수
    override fun createFragment(position: Int): Fragment {
        //1번 viewpager
        if (position % 2 == 0) {
            return MainFragment()

        }
        //2번 viewpager
        else {
            return SubFragment()
        }
    }
}
