package com.example.myapplication2

import androidx.compose.ui.text.toUpperCase
import java.lang.Exception

fun change(a: String): String {
    var islower: Boolean = true
    var notlower = ""
    ////////////////Todo////////////////
    try {
        for (i in a)
            if (!i.isLowerCase()) {
                notlower += i
                islower = false
            }
        if (!islower)
            throw Exception("error with $notlower")
    } catch (e : Exception) {
        return e.message!!
    }
    return a.toUpperCase()
}

fun main(){
    var a = "abcd"
    println(change(a))

    var b = "EfgH"
    println(change(b))

    var c = "!@%$"
    println(change(c))
}
