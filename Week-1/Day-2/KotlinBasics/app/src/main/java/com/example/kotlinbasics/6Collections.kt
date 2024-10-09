package com.example.kotlinbasics

fun main() {
    val states = listOf("Florida", "Alaska", "Hawaii", "Georgia", "Arizona")

    println(states[0])

    val cities = mutableListOf("San Francisco", "New York", "Chicago", "Detroit", "Austin")

    println(cities[0])

    cities.add("Delaware")

    println(cities)

    cities.remove(cities[1])

    println(cities)
}