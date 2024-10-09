package com.example.kotlinbasics

fun main() {

    // Type inference: Kotlin detects the variable data types without disclosure
    val myName: String = "John"
    var myAge: Int = 40

    println(myName)
    println(myAge)

    println("")

    // Int
    var hugeInt: Int = Int.MAX_VALUE
    var tinyInt: Int = Int.MIN_VALUE

    println("Maximum value of integer is " + hugeInt)
    println("Minimum value of integer is $tinyInt") // Using string interpolation in this section, really powerful and preferred way in Kotlin

    println("")

    // Double
    var hugeDouble: Double = Double.MAX_VALUE
    var tinyDouble: Double = Double.MIN_VALUE

    println("Maximum value of double is " + hugeDouble)
    println("Minimum value of double is $tinyDouble")

    println("")

    // Float
    var hugeFloat: Float = Float.MAX_VALUE
    var tinyFloat: Float = Float.MIN_VALUE

    println("Maximum value of float is " + hugeFloat)
    println("Minimum value of float is $tinyFloat")
}