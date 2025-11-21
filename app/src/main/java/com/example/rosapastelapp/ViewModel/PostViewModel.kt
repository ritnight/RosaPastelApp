package com.example.rosapastelapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rosapastelapp.data.model.Post
import com.example.rosapastelapp.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    // Flujo mutable que contiene la lista de posts
    private val _postList = MutableStateFlow<List<Post>>(emptyList())
    // Flujo público de solo lectura
    val postList: StateFlow<List<Post>> = _postList

    init {
        fetchPosts()
    }

    // Función que obtiene los datos en segundo plano
    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                _postList.value = repository.getPosts()
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error al obtener datos: ${e.localizedMessage}")
            }
        }
    }
}
