package com.example.week10_1

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.* // id를 바로 사용할 수 있도록 import


class MainActivity : AppCompatActivity() {
    var database: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doButton1.setOnClickListener{
            createDatabase()
        }
        // TODO : 버튼 이벤트 처리
        doButton2.setOnClickListener{
            createTable()
        }
        doButton3.setOnClickListener{
            addData()
        }
        doButton4.setOnClickListener{
            updateData()
        }
        doButton5.setOnClickListener{
            queryData()
        }
        doButton6.setOnClickListener{
            deleteData()
        }
    }

    //데이터 베이스 생성
    fun createDatabase(){
        // TODO : database 생성
        database = openOrCreateDatabase("people", MODE_PRIVATE, null)
        output1.append("데이터베이스 생성 또는 오픈함\n")
    }

    //테이블 생성
    fun createTable(){
        if(database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }
        // TODO : table 생성
        database?.execSQL("create table if not exists student (id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)")
        output1.append("테이블 생성함\n")
    }

    //데이터 추가
    fun addData(){
        if (database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }
        // TODO : 데이터 추가
        database?.execSQL("insert into student (name, age, mobile) values ('john', '20', '010-0000-0000')")
        output1.append("데이터 추가함\n")
    }

    fun updateData() {
        if (database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }

        //database = openOrCreateDatabase("people", MODE_PRIVATE, null)
        var values
        values.put("name", "mike")
        values.put("age", "24")
        values.put("mobile", "010-4000-4000")
        var arr : Array<String> = arrayOf("john")
        database?.update("student", values, "name=?", arr)
        output1.append("데이터 갱신\n")
    }

    //데이터 조회
    fun queryData(){
        if(database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }
        // TODO : 데이터 조회
        val cursor = database?.rawQuery("select _id, name, age, mobile from student", null)
        if (cursor != null) {
            for (index in 0 until cursor.count) {
                cursor.moveToNext()
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val mobile = cursor.getString(3)
                output1.append("레코드#${index} : $id, $name, $age, $mobile\n")
            }
            cursor.close()
        }
        output1.append("데이터 조회 결과\n")
    }

    fun deleteData() {
        if(database == null){
            output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return
        }

        val sql = "select _id, name, age, mobile from student"
        val cursor = database?.rawQuery(sql, null)
        if (cursor != null) {
            cursor.count
            val count = cursor.count
            cursor.close()

            val delete = "delete from student where _id = ${count}"            
            database?.execSQL(delete)
            output1.append("데이터 삭제\n")
        }
    }
}
