# Android Kotlin Development Guide

## 1. Introduction to Activity and Navigation Using Intents

### What is an Activity?
An **Activity** in Android represents a single screen with a user interface. It's the core component of any Android app, and each activity is associated with a layout that defines its UI.

When an app is launched, its main `Activity` is started, and each activity has its own lifecycle (from creation to destruction). Activities are defined in the AndroidManifest.xml file.

### Activity Lifecycle
An Activity goes through several stages, or "lifecycle" states, which include:
- **onCreate()**: Called when the activity is first created. This is where you initialize the activity.
- **onStart()**: Called when the activity becomes visible to the user.
- **onResume()**: Called when the user can interact with the activity.
- **onPause()**: Called when another activity comes into focus, partially obscuring the current one.
- **onStop()**: Called when the activity is no longer visible.
- **onDestroy()**: Called before the activity is destroyed.

### Navigating Between Activities Using `Intent`
In Android, **Intents** are used to navigate from one activity to another. An Intent is an abstract description of an operation to be performed.

#### Types of Intents:
- **Explicit Intent**: Used to start an activity within the same app by specifying the exact class name.
- **Implicit Intent**: Used to perform an action, such as opening a URL, without specifying the target activity. The system decides which app can handle the action.

#### Example: Explicit Intent
Here’s how you can navigate from one activity to another using an Explicit Intent.

1. **MainActivity.kt** (first activity):
```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // Create an Intent to start SecondActivity
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
```

2. **SecondActivity.kt** (second activity):
```kotlin
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}
```

In this example, clicking the button on `MainActivity` will launch `SecondActivity`.

#### Passing Data Between Activities
You can pass data between activities using `Intent` extras. For example, to send a string to another activity:
```kotlin
val intent = Intent(this, SecondActivity::class.java)
intent.putExtra("message", "Hello, Second Activity!")
startActivity(intent)
```

In the receiving activity, retrieve the data like this:
```kotlin
val message = intent.getStringExtra("message")
```

---

## 2. Handling User Input with EditText and Displaying Outputs

### What is `EditText`?
`EditText` is a widget that allows users to input text. It is typically used in forms or input fields, such as entering a username or password.

#### Example XML:
```xml
<EditText
    android:id="@+id/editText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Enter your name" />
```

### Retrieving Input from `EditText`
To get the user input, you need to access the `EditText` and retrieve the text.

Here’s how to get text from an `EditText` and display it in a `TextView`:
```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        button.setOnClickListener {
            val userInput = editText.text.toString()  // Retrieve user input
            textView.text = "You entered: $userInput"  // Display the input in TextView
        }
    }
}
```

### Input Validation
It is essential to validate the input to ensure users provide the required data. You can perform validation checks like:
- Checking if the input is empty.
- Checking if it meets certain criteria (like a valid email format).

#### Example:
```kotlin
button.setOnClickListener {
    val userInput = editText.text.toString()

    if (userInput.isEmpty()) {
        editText.error = "This field cannot be empty"
    } else {
        textView.text = "You entered: $userInput"
    }
}
```

### Handling Different Input Types
You can specify the type of input expected in the `EditText` using the `inputType` attribute in XML, such as:
- `text` for plain text.
- `number` for numerical input.
- `textPassword` for password input.

```xml
<EditText
    android:id="@+id/numberEditText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:inputType="number"
    android:hint="Enter a number" />
```

---

## 3. Event Handling for Buttons Using `onClick`

### Handling Button Clicks
Event handling is crucial for creating interactive apps. The most common event is handling button clicks using the `onClick` listener.

There are two main ways to handle button clicks in Android:

### 1. Using `setOnClickListener`
The most common method is setting a click listener in your Kotlin code.

#### Example:
```kotlin
val button = findViewById<Button>(R.id.button)
button.setOnClickListener {
    // Code to execute when the button is clicked
    Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
}
```

In this example, when the button is clicked, a message (toast) is displayed.

### 2. Declaring `onClick` in XML
You can also declare the `onClick` attribute in XML and link it to a method in your Activity.

#### Example XML:
```xml
<Button
    android:id="@+id/button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Click Me"
    android:onClick="onButtonClick" />
```

#### Example Kotlin Method:
```kotlin
fun onButtonClick(view: View) {
    // Code to execute when the button is clicked
    Toast.makeText(this, "Button Clicked via XML", Toast.LENGTH_SHORT).show()
}
```

In this case, when the button is clicked, the `onButtonClick()` method is called.

### Common Use Cases for Button Clicks
- **Navigating to another activity**: You can trigger a new activity using an Intent when a button is clicked.
- **Displaying a message**: Use `Toast` to display a short message to the user.
- **Changing UI elements**: You can dynamically change the properties of views, like text or visibility, when a button is clicked.

#### Example: Changing Text on Button Click
```kotlin
button.setOnClickListener {
    textView.text = "Button Clicked!"
}
```

### Handling Multiple Buttons
If you have multiple buttons, you can assign individual click listeners to each button, or use a more scalable approach, like a `when` statement, to handle multiple click events.

#### Example:
```kotlin
val button1 = findViewById<Button>(R.id.button1)
val button2 = findViewById<Button>(R.id.button2)

val onClickListener = View.OnClickListener { view ->
    when (view.id) {
        R.id.button1 -> {
            // Handle Button 1 click
        }
        R.id.button2 -> {
            // Handle Button 2 click
        }
    }
}

button1.setOnClickListener(onClickListener)
button2.setOnClickListener(onClickListener)
```

---