## Project Overview

We will create an Android app that dynamically displays a list of items using `RecyclerView`. The project will cover:
- **RecyclerView** basics, including how to use **Adapter** and **ViewHolder**.
- Creating a dynamic list using **LinearLayoutManager**.
- Optimizing RecyclerView performance using **DiffUtil**.
- Introducing **data binding** in XML for dynamic UI updates.

The project will be built incrementally, so students can understand the entire development process.

---

### Step 1: Set Up the Project

1. **Create a New Android Project** in Android Studio:
   - Select **Empty Activity**.
   - Name the project **"DynamicListDemo"**.
   - Make sure the language is set to **Kotlin**.

2. **Enable ViewBinding and DataBinding** in your project:
   - Open `build.gradle (Module)` and add the following inside the `android {}` block:
     ```gradle
     viewBinding {
         enabled = true
     }
     dataBinding {
         enabled = true
     }
     ```
   - Click **Sync Now** to rebuild the project.

---

### Step 2: Create the RecyclerView Layout and Adapter

1. **Main Layout (activity_main.xml)**:
   - Open `res/layout/activity_main.xml` and design the layout with a `RecyclerView` and an `EditText` for adding items dynamically.

   ```xml
   <LinearLayout
       xmlns:android="http://schemas.android.com/apk/res/android"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       android:orientation="vertical"
       android:padding="16dp">

       <EditText
           android:id="@+id/etNewItem"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:hint="Add new item" />

       <Button
           android:id="@+id/btnAdd"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Add Item" />

       <androidx.recyclerview.widget.RecyclerView
           android:id="@+id/recyclerView"
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           android:layout_marginTop="16dp" />
   </LinearLayout>
   ```

2. **RecyclerView Item Layout (item_view.xml)**:
   - Create a new layout file `item_view.xml` in `res/layout`. This layout will represent each item in the RecyclerView list.

   ```xml
   <LinearLayout
       xmlns:android="http://schemas.android.com/apk/res/android"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:orientation="vertical"
       android:padding="8dp">

       <TextView
           android:id="@+id/tvItemName"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Item Name" />
   </LinearLayout>
   ```

3. **Create a Data Model (Item.kt)**:
   - Create a Kotlin data class to represent the items in the list.
   
   ```kotlin
   data class Item(val name: String)
   ```

4. **Create an Adapter for RecyclerView (ItemAdapter.kt)**:
   - Create a new Kotlin class `ItemAdapter` that will manage the data and bind it to the `RecyclerView`.

   ```kotlin
   class ItemAdapter(private var itemList: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

       class ItemViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
           val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
           return ItemViewHolder(binding)
       }

       override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
           val item = itemList[position]
           holder.binding.tvItemName.text = item.name
       }

       override fun getItemCount(): Int {
           return itemList.size
       }

       fun updateItems(newItems: List<Item>) {
           itemList = newItems
           notifyDataSetChanged() // Refresh the entire list for now (will optimize this later with DiffUtil)
       }
   }
   ```

---

### Step 3: Set Up RecyclerView in MainActivity

1. **MainActivity.kt**:
   - Use **ViewBinding** to bind the views and set up the RecyclerView with a `LinearLayoutManager`. The user will be able to add new items dynamically by typing in the `EditText` and clicking the button.

   ```kotlin
   class MainActivity : AppCompatActivity() {

       private lateinit var binding: ActivityMainBinding
       private lateinit var adapter: ItemAdapter
       private var itemList = mutableListOf<Item>()

       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           binding = ActivityMainBinding.inflate(layoutInflater)
           setContentView(binding.root)

           // Initialize RecyclerView
           adapter = ItemAdapter(itemList)
           binding.recyclerView.layoutManager = LinearLayoutManager(this)
           binding.recyclerView.adapter = adapter

           // Add new items to the list dynamically
           binding.btnAdd.setOnClickListener {
               val newItemName = binding.etNewItem.text.toString()
               if (newItemName.isNotEmpty()) {
                   itemList.add(Item(newItemName))
                   adapter.updateItems(itemList)
                   binding.etNewItem.text.clear()
               }
           }
       }
   }
   ```

2. **Explanation**:
   - The `RecyclerView` is initialized with a `LinearLayoutManager` for a vertical list layout.
   - The `ItemAdapter` is connected to the RecyclerView, and the list is dynamically updated when the user adds new items.
   - We currently refresh the entire list using `notifyDataSetChanged()` in the `ItemAdapter`. We will optimize this with `DiffUtil` later.

---

### Step 4: Implementing DiffUtil for Efficient Updates

1. **Create a DiffUtil Callback (ItemDiffCallback.kt)**:
   - Create a new Kotlin class `ItemDiffCallback` that will compare the old and new lists to determine changes.

   ```kotlin
   class ItemDiffCallback(
       private val oldList: List<Item>,
       private val newList: List<Item>
   ) : DiffUtil.Callback() {

       override fun getOldListSize(): Int = oldList.size

       override fun getNewListSize(): Int = newList.size

       override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
           return oldList[oldItemPosition].name == newList[newItemPosition].name
       }

       override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
           return oldList[oldItemPosition] == newList[newItemPosition]
       }
   }
   ```

2. **Update Adapter to Use DiffUtil (ItemAdapter.kt)**:
   - Modify the `ItemAdapter` to use `DiffUtil` for efficient updates.

   ```kotlin
   class ItemAdapter(private var itemList: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

       class ItemViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
           val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
           return ItemViewHolder(binding)
       }

       override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
           val item = itemList[position]
           holder.binding.tvItemName.text = item.name
       }

       override fun getItemCount(): Int {
           return itemList.size
       }

       fun updateItems(newItems: List<Item>) {
           val diffCallback = ItemDiffCallback(itemList, newItems)
           val diffResult = DiffUtil.calculateDiff(diffCallback)
           itemList = newItems
           diffResult.dispatchUpdatesTo(this)
       }
   }
   ```

3. **Explanation**:
   - `DiffUtil` helps optimize RecyclerView by calculating the differences between the old and new lists, allowing efficient updates rather than refreshing the entire list.
   - The `updateItems()` method now calculates the differences and applies them to the RecyclerView efficiently.

---

### Step 5: Using Data Binding in XML

1. **Enable Data Binding in `item_view.xml`**:
   - Modify the `item_view.xml` layout to use **data binding**.

   ```xml
   <layout xmlns:android="http://schemas.android.com/apk/res/android">

       <data>
           <variable
               name="item"
               type="com.example.dynamiclistdemo.Item" />
       </data>

       <LinearLayout
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:orientation="vertical"
           android:padding="8dp">

           <TextView
               android:id="@+id/tvItemName"
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="@{item.name}" />
       </LinearLayout>
   </layout>
   ```

2. **Update the Adapter to Use Data Binding (ItemAdapter.kt)**:
   - Update the adapter to bind the data using **data binding**.

   ```kotlin
   class ItemAdapter(private var itemList: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

       class ItemViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
           val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
           return ItemViewHolder(binding)
       }

       override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
           val item = itemList[position]
           holder.binding.item = item // Bind the item to the layout using data binding
       }

       override fun getItemCount(): Int {
           return itemList.size
       }

       fun updateItems(newItems: List<Item>) {
           val diffCallback = ItemDiffCallback(itemList, newItems)
           val diffResult = DiffUtil.calculateDiff(diffCallback)
           itemList = newItems
           diffResult.dispatchUpdatesTo(this)
       }
   }
   ```

3. **Explanation**:
   - Now the `TextView` in each `RecyclerView` item is directly bound to the `Item` data using data binding.
   - The XML layout uses data binding expressions to update the UI automatically when the data changes.

---

### Step 6: Testing and Optimization

1. **Test the App**:
   - Run the app and test the functionality by adding new items to the list. Observe how the RecyclerView updates efficiently when new items are added, thanks to `DiffUtil`.

2. **Commit Changes to Git**:
   - Follow the Git workflow as per your homework assignment. Commit the changes related to the basic functionality in the `development` branch. Then, after adding `DiffUtil` for optimization, commit those changes in an `optimization` branch. Finally, merge the optimization branch into development.

---

### Conclusion

This project covers:
- Creating a dynamic list using **RecyclerView** with a custom **Adapter** and **ViewHolder**.
- Implementing efficient updates using **DiffUtil**.
- Using **ViewBinding** and **Data Binding** to bind data to the UI components.
- Dynamically adding new items to the list and managing the data efficiently.