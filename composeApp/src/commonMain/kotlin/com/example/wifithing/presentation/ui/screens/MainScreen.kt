package com.example.wifithing.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize
import com.example.wifithing.presentation.ui.components.LampCard
import com.example.wifithing.presentation.ui.components.AddLampDialog
import com.example.wifithing.presentation.viewmodel.LampViewModel
import org.koin.compose.viewmodel.koinViewModel
import com.example.wifithing.presentation.ui.theme.RuniqueBlack
import com.example.wifithing.presentation.ui.theme.RuniqueAccent

@Composable
fun MainScreen(viewModel: LampViewModel = koinViewModel()) {
    val lamps by viewModel.lamps.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RuniqueBlack)
    ) {

        var size by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { coords ->
                    size = coords.size.toSize()
                }
                .background(
                    if (size.width > 0f)
                        Brush.radialGradient(
                            colors = listOf(
                                Color(0xFF0AFF85),
                                Color(0xFF007A3A),
                                Color.Black
                            ),
                            center = Offset(
                                size.width * 0.5f,
                                size.height * -0.1f
                            ),

                            radius = size.maxDimension * 0.3f
                        )
                    else Brush.radialGradient(listOf(Color.Black, Color.Black))
                )
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 170.dp)
        ) {


            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 40.dp)   // 🔼 podnosi przycisk ~40dp wyżej
            ) {
                items(lamps) { lamp ->
                    LampCard(
                        lamp = lamp,
                        onToggle = { viewModel.toggleLamp(lamp) },
                        onDelete = { viewModel.deleteLamp(lamp) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showAddDialog = true }
                    .background(
                        RuniqueAccent,
                        RoundedCornerShape(14.dp)
                    )
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+ Dodaj urządzenie",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            if (showAddDialog) {
                AddLampDialog(
                    onDismiss = { showAddDialog = false },
                    onSave = { name, ip ->
                        viewModel.addLamp(name, ip)
                        showAddDialog = false
                    }
                )
            }
        }
    }
}
