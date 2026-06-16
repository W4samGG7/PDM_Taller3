package com.pdmcourse2026.basictemplate.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.pdmcourse2026.basictemplate.model.Option

@Composable
fun ResultCard(option: Option, onClick:() -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth().height(100.dp).clickable{onClick()},
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = option.name, fontWeight = FontWeight.Bold)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${option.votes}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text(text = "votos")
            }
        }
    }
}