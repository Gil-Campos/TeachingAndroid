package com.example.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FirstViewModel : ViewModel() {

    private val retrofit = RetrofitInstance.api

    private val _allPost = MutableLiveData<List<PostStructure>>()
    val allPost: LiveData<List<PostStructure>> get() = _allPost

    fun getAllPosts() {
        viewModelScope.launch {
            _allPost.postValue(retrofit.getAllPost())
        }
    }
}