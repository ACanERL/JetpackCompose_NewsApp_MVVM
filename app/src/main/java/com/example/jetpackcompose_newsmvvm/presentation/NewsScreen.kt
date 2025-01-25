package com.example.jetpackcompose_newsmvvm.presentation



import AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpackcompose_newsmvvm.components.CategoryChip
import com.example.jetpackcompose_newsmvvm.components.NewsCardRow
import com.example.jetpackcompose_newsmvvm.components.indicator.ErrorIndicator
import com.example.jetpackcompose_newsmvvm.components.indicator.LoadingIndicator
import com.example.jetpackcompose_newsmvvm.data.dto.Article


@Composable
fun NewsScreen(viewModel: NewsViewModel,onArticleClick: (Article) -> Unit) {
    var query by remember { mutableStateOf("") }
    val articles = viewModel.getBreakingNews(query).collectAsLazyPagingItems()
    val categories = listOf("General", "Technology", "Sports", "Health", "Science", "Business","Entertainment")
    var selectedCategory by remember { mutableStateOf(categories[0]) }
   // var searchText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize().background(AppTheme.colorScheme.backgroundColor)
            .padding(top = 32.dp, start = 8.dp, end = 8.dp)
    ) {

        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearchClick = {
                articles.refresh()
            }
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            items(categories.size) { index ->
                val category = categories[index]
                CategoryChip(
                    category = category,
                    isSelected = category == selectedCategory,
                    onClick = {
                        selectedCategory = category
                        query = category.lowercase() // API ile eşleşen bir query kullanın
                    }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.background(AppTheme.colorScheme.backgroundColor),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(articles.itemCount) { index ->
                val article = articles[index]
                article?.let {
                    NewsCardRow(
                        article = it,onArticleClick
                    )
                }
            }

            when (articles.loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        LoadingIndicator("Loading News...")
                    }
                }
                is LoadState.Error -> {
                    item {
                        ErrorIndicator("Error loading news.")
                    }
                }
                else -> {}
            }

            when (articles.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        LoadingIndicator("Loading more articles...")
                    }
                }
                is LoadState.Error -> {
                    item {
                        ErrorIndicator("Error loading more articles.")
                    }
                }
                else -> {}
            }
        }
    }
}


@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Search news...", color = AppTheme.colorScheme.textColor, style = AppTheme.typography.titleSmall)
        },
        trailingIcon = {
            androidx.compose.material3.IconButton(onClick = onSearchClick) {
                androidx.compose.material3.Icon(
                    imageVector = androidx.compose.material.icons.Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        singleLine = true
    )
}





