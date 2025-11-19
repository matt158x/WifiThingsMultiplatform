package com.example.wifithing.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wifithing.domain.model.Lamp

@Composable
fun LampCard(
    lamp: Lamp,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    val neonBrush = Brush.linearGradient(
        listOf(Color(0xFF0AFF85), Color(0xFF079A50))
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .shadow(
                    elevation = 12.dp,
                    spotColor = Color(0xFF4AF2C8).copy(alpha = 0.2f),
                    shape = RoundedCornerShape(20.dp)
                )
                .background(Color(0xFF111418), RoundedCornerShape(20.dp))
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .padding(start = 18.dp)
                        .size(26.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        Modifier.size(25.dp),
                        tint = if (lamp.isOn) Color.Gray else Color(0xFFFFFF00)
                    )
                }

                Spacer(Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = lamp.name,
                        color = Color.White,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .fillMaxHeight()
                        .background(
                            brush = if (lamp.isOn) neonBrush else SolidColor(Color(0xFF1A1D22)),
                            shape = RoundedCornerShape(
                                topEnd = 20.dp,
                                bottomEnd = 20.dp
                            )
                        )
                        .clickable { onToggle() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (lamp.isOn) "On" else "Off",
                        color = if (lamp.isOn) Color.Black else Color.Gray,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(30.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete",
                tint = Color(0xFFD9534F),
                modifier = Modifier.clickable { onDelete() }
            )
        }
    }
}

