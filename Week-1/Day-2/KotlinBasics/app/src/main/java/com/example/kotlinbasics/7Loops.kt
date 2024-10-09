package com.example.kotlinbasics

fun main() {
    val states = listOf("Florida", "Alaska", "Hawaii", "Georgia", "Arizona")

    states.forEach {
        if (it == states.last()) {
            print(it)
            println("")
            println("")
        } else {
            print("$it, ")
        }
    }

    //For Loop
    for (i in 1..5) {
        println(i)
    }

    println("")

    for (i in 1 until 5) {
        println(i)
    }

    println("")

    //While Loop
    var index = 0
    while (index < states.size) {
        println("State at $index index is ${states[index]}")
        index++
    }
}