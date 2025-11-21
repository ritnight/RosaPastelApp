package com.example.rosapastelapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rosapastelapp.ViewModel.PostViewModel
import com.example.rosapastelapp.data.model.Post

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    viewModel: PostViewModel = viewModel()
) {
    // Observamos el flujo de datos del ViewModel
    val posts by viewModel.postList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Listado de Posts") }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            PostList(posts = posts)
        }
    }
}

@Composable
private fun PostList(posts: List<Post>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(posts) { post ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = "Título: ${post.title}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = post.body,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
