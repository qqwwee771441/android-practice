package com.example.bookmark

import android.R
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmark.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    var database: SQLiteDatabase? = null
    val databaseName = "bookmark"
    val tableName = "bookmark"
    val fieldName = "link"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null)
        database?.execSQL("create table if not exists $tableName (id integer PRIMARY KEY autoincrement, $fieldName text)")

        val datas = mutableListOf<String>()

        val cursor = database?.rawQuery("select $fieldName from $tableName", null)
        if (cursor != null) {
            for (index in 0 until cursor.count) {
                cursor.moveToNext()
                val link = cursor.getString(0).toString()
                datas.add(link)
            }
            cursor.close()
        }

        val myAdapter = MyAdapter(datas, database, this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        binding.button.setOnClickListener {
            val text = binding.editText.text.toString()
            database?.execSQL("insert into $tableName ($fieldName) values ('$text')")
            datas.add(text)
            myAdapter.notifyItemInserted(datas.size)
            binding.editText.text.clear()
        }
    }
}
