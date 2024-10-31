# Android Project: Using ViewModel and LiveData with MVVM Architecture

## Project Overview

In this project, we’ll create a **To-Do List** app that demonstrates:
- **Jetpack ViewModel** and **LiveData** for managing UI-related data.
- Observing **LiveData** in **activities** and **fragments**.
- **MVVM Architecture** and why it’s useful for separating concerns and making the code more maintainable.

This project goes beyond the homework by incorporating MVVM architecture and ensuring data persistence through configuration changes.

---

### Step 1: Set Up the Project

1. **Create a New Android Project**:
   - Select **Empty Activity**.
   - Name the project **"ToDoMVVMApp"**.
   - Make sure **Kotlin** is selected as the language.

2. **Enable ViewBinding**:
   - Open `build.gradle (Module)` and add the following inside the `android {}` block:
     ```gradle
     viewBinding {
         enabled = true
     }
     ```
   - Sync the project.

3. **Add Dependencies for ViewModel and LiveData**:
   - Add the **ViewModel** and **LiveData** dependencies in `build.gradle`.

   ```gradle
   implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'
   implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.1'
   ```

---

### Step 2: Introduction to MVVM Architecture

**MVVM (Model-View-ViewModel)** is an architecture pattern that helps separate your app’s code into three main components:

1. **Model**: Responsible for the data (data classes, repositories, API calls).
2. **View**: The UI layer (activities, fragments) that displays the data.
3. **ViewModel**: Holds UI-related data and exposes it to the **View**. It also manages **LiveData** for reactive updates and persists data during configuration changes.

Using **MVVM** makes the codebase cleaner, more modular, and easier to test.

---

### Step 3: Setting Up the Model for the To-Do List

1. **Create a Data Class for To-Do Items**:
   - Create a `TodoItem.kt` file to define the model.

   ```kotlin
   data class TodoItem(
       val id: Int,
       val description: String,
       val isCompleted: Boolean = false
   )
   ```

2. **Simulate a Repository**:
   - Create a `TodoRepository.kt` file to act as a data source for the to-do items. In a real app, this would connect to a database or network API, but here we’ll keep it simple.

   ```kotlin
   object TodoRepository {

       private val todoList = mutableListOf<TodoItem>()
       private var idCounter = 1

       fun addTodo(description: String) {
           todoList.add(TodoItem(id = idCounter++, description = description))
       }

       fun getTodos(): List<TodoItem> {
           return todoList
       }
   }
   ```

---

### Step 4: Setting Up the ViewModel

1. **Create the ToDoViewModel**:
   - Create a `ToDoViewModel.kt` file. The **ViewModel** will manage the data and expose it to the **View** through **LiveData**.

   ```kotlin
   class ToDoViewModel : ViewModel() {

       private val _todos = MutableLiveData<List<TodoItem>>()
       val todos: LiveData<List<TodoItem>> get() = _todos

       init {
           loadTodos()
       }

       private fun loadTodos() {
           _todos.value = TodoRepository.getTodos()
       }

       fun addTodo(description: String) {
           TodoRepository.addTodo(description)
           loadTodos() // Update LiveData with the new list
       }
   }
   ```

2. **Explanation**:
   - `_todos` is a **MutableLiveData** that holds a list of `TodoItem`s. The external `todos` property is **LiveData**, so the **View** can observe it.
   - `addTodo()` adds a new item to the repository and reloads the data, updating `_todos` to notify observers.

---

### Step 5: Setting Up the View (Activity) to Observe LiveData

1. **Create the Layout (activity_main.xml)**:
   - Set up the UI with an **EditText** for entering new to-do items, a **Button** for adding them, and a **RecyclerView** for displaying the list.

   ```xml
   <LinearLayout
       xmlns:android="http://schemas.android.com/apk/res/android"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       android:orientation="vertical"
       android:padding="16dp">

       <EditText
           android:id="@+id/etNewTodo"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:hint="New to-do item" />

       <Button
           android:id="@+id/btnAdd"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Add" />

       <androidx.recyclerview.widget.RecyclerView
           android:id="@+id/recyclerView"
           android:layout_width="match_parent"
           android:layout_height="match_parent" />
   </LinearLayout>
   ```

2. **Set Up RecyclerView Adapter**:
   - Create `TodoAdapter.kt` to bind the list of to-do items to the RecyclerView.

   ```kotlin
   class TodoAdapter(private val todos: List<TodoItem>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

       inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
           val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
           return TodoViewHolder(binding)
       }

       override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
           val todo = todos[position]
           holder.binding.tvDescription.text = todo.description
           holder.binding.checkboxCompleted.isChecked = todo.isCompleted
       }

       override fun getItemCount() = todos.size
   }
   ```

3. **Create the MainActivity to Observe the LiveData**:
   - Set up **ViewBinding** and observe the **LiveData** from `ToDoViewModel`.

   ```kotlin
   class MainActivity : AppCompatActivity() {

       private lateinit var binding: ActivityMainBinding
       private lateinit var viewModel: ToDoViewModel
       private lateinit var adapter: TodoAdapter

       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           binding = ActivityMainBinding.inflate(layoutInflater)
           setContentView(binding.root)

           viewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)

           // Observe LiveData from ViewModel
           viewModel.todos.observe(this, Observer { todos ->
               adapter = TodoAdapter(todos)
               binding.recyclerView.adapter = adapter
           })

           // Handle adding a new to-do item
           binding.btnAdd.setOnClickListener {
               val description = binding.etNewTodo.text.toString()
               if (description.isNotBlank()) {
                   viewModel.addTodo(description)
                   binding.etNewTodo.text.clear()
               }
           }
       }
   }
   ```

4. **Explanation**:
   - The **ViewModel** is observed for changes in the `todos` LiveData. When a new item is added, the **RecyclerView** is updated automatically.
   - The **addTodo()** method in the **ViewModel** updates the list in the repository and refreshes the **LiveData**, which triggers the observer in **MainActivity**.

---

### Step 6: Test and Review MVVM in Action

**Test the App**:
- Run the app, enter a new to-do item, and press "Add." Observe that the item is added to the list.
- Test rotating the device to see how **ViewModel** and **LiveData** preserve the data during configuration changes.

### Summary of MVVM

- **Model**: The `TodoRepository` manages the data source.
- **ViewModel**: The `ToDoViewModel` provides a clean API for the **View** to interact with, holding data in **LiveData**.
- **View**: The `MainActivity` observes **LiveData** from the **ViewModel**, updating the UI automatically when data changes.