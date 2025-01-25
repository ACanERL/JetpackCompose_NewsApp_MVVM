package com.example.jetpackcompose_newsmvvm.components

import AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetpackcompose_newsmvvm.data.dto.Article

@Composable
fun NewsCardRow(article: Article,onArticleClick: (Article) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth().background(AppTheme.colorScheme.cardItemColor)
            .padding(8.dp).clickable { onArticleClick(article) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Görsel
            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Metinler
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = article.title ?: "Başlık Bulunamadı",
                    style = AppTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    color = AppTheme.colorScheme.textColor,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.description ?: "Açıklama Bulunamadı",
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colorScheme.textColor,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Published at: ${article.publishedAt ?: "N/A"}",
                    style = AppTheme.typography.titleSmall,
                    color = AppTheme.colorScheme.textColor,
                )
            }
        }
    }
}