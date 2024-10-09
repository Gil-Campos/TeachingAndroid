package com.example.kotlinbasics

fun main() {
    val name = "Britney"
    val surname = "Spears"
    val fullName = name + (" ") + surname
    println(fullName)


    val num1 = 10
    val num2 = 5
    val addition = num1 + num2
    val subtraction = num1 - num2
    val multiplication = num1 * num2
    val division = num1 / num2
    val modulo = num1 % num2

    println("Addition of 10 and 5 = $addition")
    println("Subtraction of 10 and 5 = $subtraction")
    println("Multiplication of 10 and 5 = $multiplication")
    println("Division of 10 and 5 = $division")
    println("Modulo of 10 and 5 = $modulo")

}