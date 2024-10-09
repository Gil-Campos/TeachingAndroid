# Android Kotlin Development Basic Layouts

## 1. XML Layout System and ViewBinding

### XML Layouts in Android
In Android, XML (Extensible Markup Language) is used to define the user interface. Each layout file represents a UI screen and contains View elements like `TextView`, `Button`, `EditText`, `LinearLayout`, etc.

Example XML Layout (`activity_main.xml`):
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello, Android!" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Click Me" />
</LinearLayout>
```

### Why ViewBinding is Better Than `findViewById()`
In older Android development, UI components were accessed using `findViewById()`. This approach is error-prone and can lead to runtime crashes if the wrong view is referenced, or if a view is not present in the layout.

**ViewBinding** solves this by:
- Type-safe access to views.
- Null-safe access: If a view is not present, you will get a compile-time error rather than a runtime error.
- No need to cast views manually (like with `findViewById()`).

#### Enabling ViewBinding
To use ViewBinding in your project:
1. Open `build.gradle` (Module) and add the following inside `android {}` block:
    ```gradle
    viewBinding {
        enabled = true
    }
    ```
2. Rebuild the project. A binding class will be generated for each XML file.

#### Using ViewBinding:
1. In your Activity:
    ```kotlin
    class MainActivity : AppCompatActivity() {

        // Define a binding variable for the activity
        private lateinit var binding: ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            // Initialize the binding
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Access views using binding
            binding.textView.text = "Hello, ViewBinding!"
            binding.button.setOnClickListener {
                // Handle button click
            }
        }
    }
    ```

2. No need to use `findViewById()` anymore! With ViewBinding, the views are automatically accessible.

---

## 2. TextView, Button, and EditText

### Basic Widgets

#### 1. **TextView**
`TextView` is used to display text on the screen. It can be styled, formatted, and is very flexible.
```xml
<TextView
    android:id="@+id/textView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello, World!"
    android:textSize="18sp"
    android:textColor="@android:color/black" />
```

#### 2. **Button**
`Button` allows users to perform actions when clicked. It's essential for interaction in your app.
```xml
<Button
    android:id="@+id/button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Click Me" />
```

Handling Button Click:
```kotlin
binding.button.setOnClickListener {
    // Handle button click here
}
```

#### 3. **EditText**
`EditText` is used to allow user input in the form of text. You can set different input types (text, number, password, etc.).
```xml
<EditText
    android:id="@+id/editText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Enter your name" />
```

You can retrieve text from the `EditText` like this:
```kotlin
val inputText = binding.editText.text.toString()
```

### CompatView and Material Design Components
#### Why use CompatViews?
AndroidX `AppCompat` Views provide backward compatibility for older Android versions, ensuring your app works on a wide range of devices. Always use AndroidX components when building modern apps.

#### Material Design Components
For a modern look and feel, Android provides **Material Design Components** which include:
- `com.google.android.material.button.MaterialButton`
- `com.google.android.material.textview.MaterialTextView`
- `com.google.android.material.textfield.TextInputLayout` for better `EditText` input handling.

Example of a Material Button:
```xml
<com.google.android.material.button.MaterialButton
    android:id="@+id/materialButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Material Button" />
```

Example of `TextInputLayout` (better user input handling with `EditText`):
```xml
<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Enter your name">

    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/inputEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
</com.google.android.material.textfield.TextInputLayout>
```

---

## 3. Basic Use of LinearLayout and RelativeLayout

### LinearLayout
`LinearLayout` is used to align its children in a single direction — either horizontally or vertically.

#### Key Features:
- `android:orientation="vertical"` or `android:orientation="horizontal"` to specify the direction.
- Supports `layout_weight` for proportional sizing.

Example:
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <TextView
        android:id="@+id/textView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="First TextView" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Second TextView" />
</LinearLayout>
```

#### Using `layout_weight`:
The `layout_weight` attribute allows views to expand and fill available space proportionally.
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <Button
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Button 1" />

    <Button
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Button 2" />
</LinearLayout>
```
In this example, both buttons will take up 50% of the available width.

### RelativeLayout (deprecated in favor of `ConstraintLayout`)
`RelativeLayout` is used to position its children relative to each other or the parent. However, it is mostly replaced by `ConstraintLayout` due to its better performance and flexibility.

#### Key Features:
- Views can be aligned relative to each other using `layout_toRightOf`, `layout_below`, etc.
- Views can be aligned relative to the parent using `layout_alignParentTop`, `layout_centerInParent`, etc.

Example:
```xml
<RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Above Button"
        android:layout_centerInParent="true" />

    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Click Me"
        android:layout_below="@id/textView1"
        android:layout_centerHorizontal="true" />
</RelativeLayout>
```

---

This guide will help your students understand the essential components of Android development with Kotlin.
