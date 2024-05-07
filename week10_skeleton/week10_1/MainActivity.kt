package com.choi.myapplication

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.choi.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var database: SQLiteDatabase? = null
    private lateinit var binding:ActivityMainBinding
    val databaseName = "people"
    val tableName = "student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.doButton1.setOnClickListener{
            createDatabase()
        }
        // TODO : 버튼 이벤트 처리
        binding.doButton2.setOnClickListener{
            createTable()
        }
        binding.doButton3.setOnClickListener{
            addData()
        }
        binding.doButton4.setOnClickListener{
            updateData()
        }
        binding.doButton5.setOnClickListener{
            queryData()
        }
        binding.doButton6.setOnClickListener{
            deleteData()
        }
    }

    //데이터 베이스 생성
    fun createDatabase(){
        // TODO : database 생성
        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null)
        binding.output1.append("데이터베이스 생성 또는 오픈함\n")
    }

    //테이블 생성
    fun createTable(){
        if(database == null){
            binding.output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }
        // TODO : table 생성
        database?.execSQL("create table if not exists $tableName (id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)")
        binding.output1.append("테이블 생성함\n")
    }

    //데이터 추가
    fun addData(){
        if (database == null){
            binding.output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }
        // TODO : 데이터 추가
        database?.execSQL("insert into $tableName (name, age, mobile) values ('john', '20', '010-0000-0000')")
        binding.output1.append("데이터 추가함\n")
    }

    fun updateData() {
        if (database == null){
            binding.output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }

        val values = ContentValues()
        values.put("name", "mike")
        values.put("age", "24")
        values.put("mobile", "010-4000-4000")
        var arr : Array<String> = arrayOf("john")
        database?.update(tableName, values, "name=?", arr)
        binding.output1.append("데이터 갱신\n")
    }

    //데이터 조회
    fun queryData(){
        if(database == null){
            binding.output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }
        // TODO : 데이터 조회
        val cursor = database?.rawQuery("select id, name, age, mobile from $tableName", null)
        if (cursor != null) {
            for (index in 0 until cursor.count) {
                cursor.moveToNext()
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val mobile = cursor.getString(3)
                binding.output1.append("레코드#${index} : $id, $name, $age, $mobile\n")
            }
            cursor.close()
        }
        binding.output1.append("데이터 조회 결과\n")
    }

    fun deleteData() {
        if(database == null){
            binding.output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }

        val sql = "select id, name, age, mobile from $tableName"
        val cursor = database?.rawQuery(sql, null)
        if (cursor != null) {
            cursor.count
            val count = cursor.count
            cursor.close()

            val delete = "delete from $tableName where id = ${count}"
            database?.execSQL(delete)
            binding.output1.append("데이터 삭제\n")
        }
    }
}
