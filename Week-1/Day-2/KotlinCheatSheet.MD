# Kotlin Cheat Sheet for Android Developers

## 1. Variables
In Kotlin, you declare variables using `val` (immutable) or `var` (mutable).

- `val` for read-only (constant) variables:
  ```kotlin
  val name: String = "Android"
  ```

- `var` for mutable variables:
  ```kotlin
  var age: Int = 21
  ```

You can omit the type if it's inferred:
```kotlin
val name = "Kotlin"
var age = 21
```

## 2. Operators
Kotlin supports a variety of operators.

### Arithmetic Operators:
- `+`, `-`, `*`, `/`, `%`
  ```kotlin
  val sum = 5 + 3
  val product = 5 * 3
  ```

### Assignment Operators:
- `=`, `+=`, `-=`, `*=`, `/=`, `%=`
  ```kotlin
  var x = 10
  x += 5  // x = 15
  ```

### Comparison Operators:
- `==`, `!=`, `>`, `<`, `>=`, `<=`
  ```kotlin
  val isEqual = (5 == 5)  // true
  ```

### Logical Operators:
- `&&`, `||`, `!`
  ```kotlin
  val isTrue = (5 > 3 && 3 < 10)  // true
  ```

## 3. Strings
Strings in Kotlin are immutable sequences of characters.

- Declaring a string:
  ```kotlin
  val text = "Hello, Android"
  ```

- String interpolation:
  ```kotlin
  val name = "Kotlin"
  val greeting = "Hello, $name"
  ```

- Multi-line strings:
  ```kotlin
  val multiline = """
      |Hello,
      |Android Developer
  """.trimMargin()
  ```

## 4. Conditionals
Kotlin offers standard conditional statements like `if`, `else`, `when`.

### `if-else` statement:
```kotlin
val max = if (a > b) a else b
```

### `when` statement (like a switch):
```kotlin
val result = when (x) {
    1 -> "One"
    2 -> "Two"
    else -> "Unknown"
}
```

## 5. Collections
Kotlin offers immutable (`List`, `Set`, `Map`) and mutable (`MutableList`, `MutableSet`, `MutableMap`) collections.

### Lists:
- Immutable list:
  ```kotlin
  val numbers = listOf(1, 2, 3)
  ```

- Mutable list:
  ```kotlin
  val mutableNumbers = mutableListOf(1, 2, 3)
  mutableNumbers.add(4)
  ```

### Maps:
- Immutable map:
  ```kotlin
  val map = mapOf(1 to "one", 2 to "two")
  ```

- Mutable map:
  ```kotlin
  val mutableMap = mutableMapOf(1 to "one")
  mutableMap[2] = "two"
  ```

### Sets:
- Immutable set:
  ```kotlin
  val set = setOf(1, 2, 3)
  ```

- Mutable set:
  ```kotlin
  val mutableSet = mutableSetOf(1, 2)
  mutableSet.add(3)
  ```

## 6. Loops
Kotlin supports various loop types like `for`, `while`, `do-while`.

### `for` loop:
```kotlin
for (i in 1..5) {
    println(i)
}
```

### Loop over a collection:
```kotlin
for (item in listOf("A", "B", "C")) {
    println(item)
}
```

### `while` loop:
```kotlin
var x = 5
while (x > 0) {
    println(x)
    x--
}
```

### `do-while` loop:
```kotlin
var y = 3
do {
    println(y)
    y--
} while (y > 0)
```

## 7. Functions
Functions are declared using the `fun` keyword.

### Basic function:
```kotlin
fun greet() {
    println("Hello, Android Developer!")
}
```

### Function with parameters:
```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}
```

### Single-expression function:
```kotlin
fun multiply(a: Int, b: Int) = a * b
```

### Default arguments:
```kotlin
fun greet(name: String = "Android") {
    println("Hello, $name!")
}
```

## 8. Null Safety
Kotlin has built-in null safety, preventing null pointer exceptions.

### Nullable types:
- Use `?` to indicate a variable can hold a null value:
  ```kotlin
  var name: String? = null
  ```

### Safe calls:
- Use the safe call operator `?.` to access a property or method of a nullable type:
  ```kotlin
  val length = name?.length
  ```

### Elvis operator (`?:`):
- Provides a default value if the expression is `null`:
  ```kotlin
  val length = name?.length ?: 0
  ```

### Non-null assertions:
- Use `!!` to force a nullable type to a non-nullable type (throws an exception if the value is null):
  ```kotlin
  val length = name!!.length
  ```

---

This cheat sheet provides a quick reference to the basic Kotlin concepts needed for Android development.
