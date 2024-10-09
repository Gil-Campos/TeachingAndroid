package com.example.kotlinbasics

fun sayHello() {
    println("Hello!")
}

fun sayHelloToSomeone(name: String) {
    println("Hello, $name!")
}

fun add(): Int = 20 + 30

fun divide(x: Int, y: Int): Int {
    return x / y
}

fun main() {
    sayHello()
    sayHelloToSomeone("John")
    println(add())
    println(divide(25, 5))
}