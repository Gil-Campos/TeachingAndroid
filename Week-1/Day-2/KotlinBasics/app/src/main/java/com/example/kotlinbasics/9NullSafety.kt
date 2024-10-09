package com.example.kotlinbasics

fun main() {
    val data: String? = null

    if (data != null) {
        println(data.lowercase())
    }

    println(data?.uppercase())
}