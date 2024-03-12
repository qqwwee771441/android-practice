package com.example.myapplication

fun main() {
    val a = readLine()
    println("입력 받은 숫자: ${a!!}")
    for (i in 1 .. a.toInt())
        if (i % 2 == 1)
            print("$i ")
}
