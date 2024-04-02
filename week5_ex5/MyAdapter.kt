package com.choi.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choi.myapplication.databinding.ItemMainBinding

//항목 뷰를 가지는 역할
class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

//항목 구성자
class MyAdapter(val datas:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = datas.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = MyViewHolder(
        ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        Log.d("kkang", "onBindViewHolder : $position")

        //항목 뷰를 가지는 뷰 홀더를 준비하기 위해 자동 호출
        val binding = (holder as MyViewHolder).binding

        //TODO: 데이터를 뷰에 표시
        binding.itemData.text = datas[position]
        binding.itemRoot.setOnClickListener{
            Log.d("kkang", "item root click : $position")
        }
    }
}