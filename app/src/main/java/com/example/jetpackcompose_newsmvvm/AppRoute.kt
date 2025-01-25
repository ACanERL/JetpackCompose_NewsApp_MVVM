package com.example.jetpackcompose_newsmvvm

import AppTheme
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose_newsmvvm.presentation.DetailsScreen
import com.example.jetpackcompose_newsmvvm.presentation.NewsScreen
import com.example.jetpackcompose_newsmvvm.presentation.NewsViewModel
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun AppRoute(viewModel: NewsViewModel){
   val navController= rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "news_list") {
        composable("news_list") {
            NewsScreen(
                viewModel = viewModel,
                onArticleClick = { article ->
                    val encodedTitle = article.title?.let {
                        URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
                    }
                    val encodedDesc = article.description?.let {
                        URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
                    }
                    val encodedImageUrl = article.urlToImage?.let {
                        URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
                    }
                    val encodedContent=article.content.let {
                        URLEncoder.encode(it,StandardCharsets.UTF_8.toString())
                    }

                    if (encodedTitle.isNullOrEmpty() || encodedDesc.isNullOrEmpty() || encodedImageUrl.isNullOrEmpty()|| encodedContent.isNullOrEmpty()) {

                        Toast.makeText(context, "Haber Verisi Tamamı Yüklenmedi.", Toast.LENGTH_SHORT).show()
                    } else {
                        navController.navigate("detail_screen/$encodedTitle/$encodedDesc/$encodedImageUrl/$encodedContent")
                    }


                }
            )
        }
        composable("detail_screen/{articleTitle}/{articleDescp}/{articleUrlImg}/{articleContent}") { backStackEntry ->
            val articleTitle = backStackEntry.arguments?.getString("articleTitle")?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            }
            val articleDescp = backStackEntry.arguments?.getString("articleDescp")?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            }
            val articleUrlImg = backStackEntry.arguments?.getString("articleUrlImg")?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            }
            val articleContent=backStackEntry.arguments?.getString("articleContent").let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            }


            if (articleTitle.isNullOrEmpty() || articleDescp.isNullOrEmpty() || articleUrlImg.isNullOrEmpty()||articleContent.isNullOrEmpty()) {
                Text(text = "Haber Verisi Tamamı Yüklenmedi !", style = AppTheme.typography.titleLarge)
            } else {
                DetailsScreen(articleTitle = articleTitle, articleDescp = articleDescp, articleUrlImg = articleUrlImg,articleContent=articleContent)
            }
        }
    }
}