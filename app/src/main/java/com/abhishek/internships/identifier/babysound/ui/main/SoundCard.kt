package com.abhishek.internships.identifier.babysound.ui.main


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.abhishek.internships.identifier.babysound.model.BabySound

@Composable
fun SoundCard(sound: BabySound, bgColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = sound.sound,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.width(12.dp))
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color.White.copy(alpha = 0.6f)
                ) {
                    Text(
                        text = sound.meaning,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = sound.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

