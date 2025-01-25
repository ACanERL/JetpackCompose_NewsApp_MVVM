package com.example.jetpackcompose_newsmvvm.presentation

import AppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun DetailsScreen(articleTitle: String?, articleDescp: String?, articleUrlImg: String?,articleContent: String) {
    Column(modifier = Modifier.fillMaxSize()
        .padding(top = 32.dp, start = 4.dp, end = 4.dp).background(AppTheme.colorScheme.backgroundColor)) {
        articleTitle?.let {
            Text(modifier = Modifier.padding(6.dp), text = "Title:\n $it",
                color = AppTheme.colorScheme.textColor,
                style = MaterialTheme.typography.titleMedium)
        }
        articleDescp?.let {
            Text(modifier = Modifier.padding(6.dp),text = "Description:\n $it",
                color = AppTheme.colorScheme.textColor,
                style = MaterialTheme.typography.bodySmall)
        }
        articleUrlImg?.let {
            Image(modifier = Modifier.padding(6.dp).fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = rememberImagePainter(it),
                contentDescription = "Article Image")
        }
        articleContent.let {
            Text(modifier = Modifier.padding(6.dp),text = "Content: \n$it",
                color = AppTheme.colorScheme.textColor,
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}