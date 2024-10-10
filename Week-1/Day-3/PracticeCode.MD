# Practice Project

## 1. Introduction to Activity and Navigation Using Intents

### Step 1: Set Up the Project

1. **Create a New Android Project** in Android Studio:
   - Select **Empty Activity**.
   - Name the project **"UserInputDemo"**.
   - Make sure the language is set to **Kotlin**.

2. **Enable ViewBinding** in your project:
   - Open `build.gradle (Module)` and add the following inside the `android {}` block:
     ```gradle
     viewBinding {
         enabled = true
     }
     ```
   - Click **Sync Now** to rebuild the project.

---

## Step 2: Create the First Activity (MainActivity)

1. **Layout (activity_main.xml)**:
   - Open `res/layout/activity_main.xml` and replace the default `ConstraintLayout` with a `LinearLayout`. Inside this layout, add `EditText` fields for the user to input their name and age, and a button to submit the data.

   ```xml
   <LinearLayout
       xmlns:android="http://schemas.android.com/apk/res/android"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       android:orientation="vertical"
       android:padding="16dp">

       <EditText
           android:id="@+id/etName"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:hint="Enter your name" />

       <EditText
           android:id="@+id/etAge"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:hint="Enter your age"
           android:inputType="number" />

       <Button
           android:id="@+id/btnSubmit"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Submit" />
   </LinearLayout>
   ```

2. **MainActivity.kt**:
   - Use ViewBinding to bind the views and handle button clicks.
   - Collect the user input and pass it to the second activity using `Intent`.

   ```kotlin
   class MainActivity : AppCompatActivity() {

       private lateinit var binding: ActivityMainBinding

       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           binding = ActivityMainBinding.inflate(layoutInflater)
           setContentView(binding.root)

           // Button click listener to handle data submission
           binding.btnSubmit.setOnClickListener {
               val name = binding.etName.text.toString()
               val age = binding.etAge.text.toString()

               // Check if input fields are empty
               if (name.isEmpty() && age.isEmpty()) {
                   Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
               } else {
                   // Create Intent to navigate to SecondActivity
                   val intent = Intent(this, SecondActivity::class.java).apply {
                       putExtra("name", name)
                       putExtra("age", age)
                   }
                   startActivity(intent)
               }
           }
       }

       override fun onStart() {
           super.onStart()
           Log.d("MainActivity", "onStart called")
       }

       override fun onResume() {
           super.onResume()
           Log.d("MainActivity", "onResume called")
       }

       override fun onPause() {
           super.onPause()
           Log.d("MainActivity", "onPause called")
       }

       override fun onStop() {
           super.onStop()
           Log.d("MainActivity", "onStop called")
       }

       override fun onDestroy() {
           super.onDestroy()
           Log.d("MainActivity", "onDestroy called")
       }
   }
   ```

3. **Explanation**:
   - We use `ViewBinding` to access the UI elements without using `findViewById()`.
   - We pass the user's name and age to `SecondActivity` using an explicit `Intent`.
   - We handle empty input fields by showing a toast message `"No data"`.
   - Logs are added to each lifecycle method to demonstrate the activity lifecycle.

---

## Step 3: Create the Second Activity (SecondActivity)

1. **Layout (activity_second.xml)**:
   - Open `res/layout/activity_second.xml` and design the layout to display the name and age passed from the first activity.

   ```xml
   <LinearLayout
       xmlns:android="http://schemas.android.com/apk/res/android"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       android:orientation="vertical"
       android:padding="16dp">

       <TextView
           android:id="@+id/tvName"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Name: " />

       <TextView
           android:id="@+id/tvAge"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Age: " />

       <Button
           android:id="@+id/btnBack"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Go Back" />
   </LinearLayout>
   ```

2. **SecondActivity.kt**:
   - Retrieve the data passed from `MainActivity` using `Intent`.
   - Display the data in `TextView` elements and provide a button to return to `MainActivity`.

   ```kotlin
   class SecondActivity : AppCompatActivity() {

       private lateinit var binding: ActivitySecondBinding

       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           binding = ActivitySecondBinding.inflate(layoutInflater)
           setContentView(binding.root)

           // Retrieve data from Intent
           val name = intent.getStringExtra("name")
           val age = intent.getStringExtra("age")

           // Handle empty fields and display user input
           binding.tvName.text = if (name.isNullOrEmpty()) "Name: Nameless" else "Name: $name"
           binding.tvAge.text = if (age.isNullOrEmpty()) "Age: Invalid Age" else "Age: $age"

           // Button to go back to MainActivity
           binding.btnBack.setOnClickListener {
               finish() // Ends current activity and goes back to MainActivity
           }
       }

       override fun onStart() {
           super.onStart()
           Log.d("SecondActivity", "onStart called")
       }

       override fun onResume() {
           super.onResume()
           Log.d("SecondActivity", "onResume called")
       }

       override fun onPause() {
           super.onPause()
           Log.d("SecondActivity", "onPause called")
       }

       override fun onStop() {
           super.onStop()
           Log.d("SecondActivity", "onStop called")
       }

       override fun onDestroy() {
           super.onDestroy()
           Log.d("SecondActivity", "onDestroy called")
       }
   }
   ```

3. **Explanation**:
   - `SecondActivity` receives the data from `MainActivity` using `Intent` and displays it in `TextView` elements.
   - If the name or age is empty, it displays "Nameless" or "Invalid Age" as required.
   - Lifecycle methods with logs are also added to demonstrate the activity lifecycle.

---

## Step 4: Test the Activity Lifecycle

1. **Understanding the Activity Lifecycle**:
   - The activity lifecycle methods (`onCreate()`, `onStart()`, `onResume()`, `onPause()`, `onStop()`, `onDestroy()`) are logged in both activities.
   - Open **Logcat** in Android Studio, filter by `MainActivity` or `SecondActivity`, and observe the logs when navigating between the two activities.

2. **Testing the Lifecycle**:
   - Start the app and check how the `MainActivity` logs its lifecycle events (`onStart()`, `onResume()`, etc.).
   - Navigate to `SecondActivity` and observe how the lifecycle of `MainActivity` changes (`onPause()`, `onStop()`) and how `SecondActivity` begins its lifecycle.
   - Press the back button and see the reverse lifecycle process as the app returns to `MainActivity`.

---

## Step 5: Further Enhancements

1. **Error Handling for Age Input**:
   - Add a check in `MainActivity` to ensure the age input is numeric. If it is not, pass "Invalid Age" to the second activity.

   ```kotlin
   val age = binding.etAge.text.toString()

   if (age.isEmpty()) {
       Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
   } else if (!age.isDigitsOnly()) {
       intent.putExtra("age", "Invalid Age")
   } else {
       intent.putExtra("age", age)
   }
   ```

2. **Navigation and Backstack**:
   - Demonstrate how activities are added to the backstack and how the user can navigate using the back button.

3. **Customization**:
   - Customize the

 `EditText` input types (e.g., `android:inputType="number"` for age, `textPersonName` for name).

---

### Conclusion

This project covers:
- Navigation between activities using `Intent`.
- Handling user input through `EditText` and displaying results.
- Event handling for buttons using `onClickListener`.
- Demonstrating the **Activity Lifecycle** using logs.