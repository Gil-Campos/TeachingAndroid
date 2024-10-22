## Project Overview

We will create an Android app with multiple fragments, manage navigation between them using the **Jetpack Navigation component**, and organize our navigation using a **navigation graph**. The project will:
- Introduce **Fragments** and explain their lifecycle.
- Use **Jetpack Navigation** for seamless fragment transactions.
- Show how to create and manage a **navigation graph** for fragment transitions.

This project will be more complex than the homework, making it a valuable reference for students’ future projects.

---

### Step 1: Set Up the Project

1. **Create a New Android Project**:
   - Select **Empty Activity**.
   - Name the project **"FragmentNavigationDemo"**.
   - Set the language to **Kotlin**.

2. **Enable ViewBinding**:
   - Open `build.gradle (Module)` and add the following inside the `android {}` block:
     ```gradle
     viewBinding {
         enabled = true
     }
     ```
   - Click **Sync Now** to rebuild the project.

3. **Add Jetpack Navigation Dependencies**:
   - In the same `build.gradle` file, add the following dependencies inside the `dependencies {}` block:
     ```gradle
     // Navigation component
     implementation 'androidx.navigation:navigation-fragment-ktx:2.6.0'
     implementation 'androidx.navigation:navigation-ui-ktx:2.6.0'
     ```
   - Sync the project to install the dependencies.

---

### Step 2: Introduction to Fragments and Fragment Lifecycle

**What are Fragments?**
Fragments are UI components that represent a portion of the screen within an activity. They are modular and can be reused within different activities. Instead of using multiple activities, fragments allow better memory management and smoother transitions between screens.

**Why Use Fragments Instead of Multiple Activities?**
- **Memory Efficiency**: Fragments share the same activity, reducing memory usage compared to using multiple activities.
- **Reusable UI Components**: Fragments can be reused across different parts of the app.
- **Smoother Transitions**: Fragment transactions allow for smooth animations and state management.

**Fragment Lifecycle:**
Just like activities, fragments have a lifecycle:
1. `onAttach()`: Called when the fragment is attached to its parent activity.
2. `onCreate()`: Initializes fragment-level resources.
3. `onCreateView()`: Inflates the fragment’s layout.
4. `onViewCreated()`: Called after the view is created.
5. `onStart()`: Called when the fragment becomes visible.
6. `onResume()`: Called when the fragment is actively running.
7. `onPause()`: Called when the fragment is partially hidden or paused.
8. `onStop()`: Called when the fragment is no longer visible.
9. `onDestroyView()`: Cleans up the fragment’s view.
10. `onDetach()`: Called when the fragment is detached from the activity.

### Example Fragment Lifecycle Logging:

```kotlin
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        Log.d("FragmentLifecycle", "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentLifecycle", "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("FragmentLifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("FragmentLifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("FragmentLifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("FragmentLifecycle", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("FragmentLifecycle", "onDestroyView")
    }
}
```

---

### Step 3: Using Jetpack Navigation Component for Fragment Transactions

**Jetpack Navigation** allows you to manage fragment transactions more easily by defining all navigation in a single XML file called a **navigation graph**.

1. **Create Fragments**:
   - Create three fragments: `FirstFragment`, `SecondFragment`, and `ThirdFragment`.
   - Each fragment will have a simple layout and log its lifecycle events for debugging.

**FirstFragment Layout (`fragment_first.xml`)**:
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <TextView
        android:id="@+id/tvFirstFragment"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="This is the First Fragment" />
</LinearLayout>
```

Repeat similar layouts for `SecondFragment` and `ThirdFragment`.

2. **Create Navigation Graph**:
   - In `res/navigation`, create a new navigation resource file called `nav_graph.xml`. This file will define the navigation between your fragments.

**nav_graph.xml**:
```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    app:startDestination="@id/firstFragment">

    <fragment
        android:id="@+id/firstFragment"
        android:name="com.example.fragmentnavigationdemo.FirstFragment"
        android:label="First Fragment"
        tools:layout="@layout/fragment_first" />

    <fragment
        android:id="@+id/secondFragment"
        android:name="com.example.fragmentnavigationdemo.SecondFragment"
        android:label="Second Fragment"
        tools:layout="@layout/fragment_second" />

    <fragment
        android:id="@+id/thirdFragment"
        android:name="com.example.fragmentnavigationdemo.ThirdFragment"
        android:label="Third Fragment"
        tools:layout="@layout/fragment_third" />
</navigation>
```

This defines three fragments and sets the `FirstFragment` as the starting destination.

3. **Set Up Navigation in Activity**:
   - Open `activity_main.xml` and replace the `ConstraintLayout` with a `FragmentContainerView` where your fragments will be displayed.

```xml
<androidx.fragment.app.FragmentContainerView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:navGraph="@navigation/nav_graph"
    app:defaultNavHost="true" />
```

4. **MainActivity.kt**:
   - Set up **NavController** in `MainActivity` to manage fragment transactions.

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize NavController
        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
```

---

### Step 4: Managing Navigation with a Navigation Graph

**Why Use a Navigation Graph?**
- It simplifies fragment transactions by centralizing all navigation in one XML file.
- You can visually manage the navigation flow between fragments.
- The navigation graph handles backstack management automatically.

### Adding Navigation Actions to Graph

1. **Define Actions in `nav_graph.xml`**:
   - Add navigation actions to define the transitions between fragments.

```xml
<fragment
    android:id="@+id/firstFragment"
    android:name="com.example.fragmentnavigationdemo.FirstFragment"
    android:label="First Fragment"
    tools:layout="@layout/fragment_first">
    <action
        android:id="@+id/action_firstFragment_to_secondFragment"
        app:destination="@id/secondFragment" />
</fragment>

<fragment
    android:id="@+id/secondFragment"
    android:name="com.example.fragmentnavigationdemo.SecondFragment"
    android:label="Second Fragment"
    tools:layout="@layout/fragment_second">
    <action
        android:id="@+id/action_secondFragment_to_thirdFragment"
        app:destination="@id/thirdFragment" />
</fragment>
```

2. **Triggering Navigation in Fragments**:
   - Add buttons in your fragment layouts to trigger navigation.

**Example in `fragment_first.xml`**:
```xml
<Button
    android:id="@+id/btnNext"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Go to Second Fragment" />
```
---

### Step 4 (Continued): Triggering Navigation in Fragments

Now, let's update the fragment to trigger navigation using the buttons. For this, we'll make use of the **NavController** to navigate between the fragments defined in the **navigation graph**.

1. **Trigger Navigation in FirstFragment**:
   - Add a button in the `fragment_first.xml` to navigate to the second fragment.
   - In `FirstFragment.kt`, we will use the **NavController** to navigate to the `SecondFragment`.

**Updated `fragment_first.xml`**:
```xml
<Button
    android:id="@+id/btnNext"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Go to Second Fragment" />
```

**FirstFragment.kt**:
```kotlin
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the click listener to navigate to SecondFragment
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```

2. **Trigger Navigation in SecondFragment**:
   - Similarly, add a button in `fragment_second.xml` to navigate to `ThirdFragment`.

**Updated `fragment_second.xml`**:
```xml
<Button
    android:id="@+id/btnNext"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Go to Third Fragment" />
```

**SecondFragment.kt**:
```kotlin
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the click listener to navigate to ThirdFragment
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```

3. **ThirdFragment**:
   - This fragment could simply display some content without navigating further, serving as the final fragment in the flow.

---

### Step 5: Why Fragments are Better than Using Lots of Activities

Using fragments instead of multiple activities offers several advantages:
1. **Memory Efficiency**: Instead of creating new activities, fragments allow for reusing the same activity while switching between different screens, saving memory.
2. **Shared Data**: Fragments can easily share data with their parent activity, making state management easier and reducing the need for complex data passing between activities.
3. **Flexible UI**: Fragments allow more flexible UI designs, such as using multiple fragments on the same screen for tablets or larger devices.
4. **Backstack Management**: The navigation component makes it easier to manage the backstack, ensuring that navigating between fragments feels seamless.

---

### Step 6: Finalizing and Testing the Project

1. **Testing Fragment Navigation**:
   - Run the app and test navigating between the fragments using the buttons. The navigation component handles fragment transactions and backstack management automatically.
   - Use **Logcat** to observe the fragment lifecycle events being logged as you navigate between fragments.