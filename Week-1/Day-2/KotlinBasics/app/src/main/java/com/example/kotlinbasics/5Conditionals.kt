package com.example.kotlinbasics

fun main() {
    var myAge = 19

    if (myAge >= 18) {
        println("You can smoke")
    } else {
        println("you cannot smoke yet!")
    }

    myAge = 35

    if (myAge <= 18) {
        println("You can smoke")
    } else if (myAge == 35) {
        println("And you can drink too!")
    } else {
        println("you cannot smoke yet!")
    }

    myAge = 135

    when (myAge) {
        20 -> println("Too young")
        35 -> println("Middle age man")
        else -> println("Not even human")
    }
}