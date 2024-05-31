package com.example.bookmark

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmark.databinding.ItemMainBinding


class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas:MutableList<String>, val database: SQLiteDatabase?, val context:Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = datas.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = MyViewHolder(
        ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("bookmark", "onBindViewHolder : $position")
        val binding = (holder as MyViewHolder).binding

        binding.itemData.text = datas[position]
        binding.itemRoot.setOnClickListener{
            Log.d("bookmark", "item click : ${datas[position]}")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(datas[position]));
            context.startActivity(intent)
        }

        binding.button.setOnClickListener {
            val sql = "select id, link from bookmark"
            val cursor = database?.rawQuery(sql, null)
            if (cursor != null) {
                var id: Int = -1
                for (index in 0 until position + 1) {
                    cursor.moveToNext()
                    id = cursor.getInt(0).toInt()
                }
                if (id != -1)
                    database?.execSQL("delete from bookmark where id = ${id}")
                cursor.close()
            }

            datas.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
