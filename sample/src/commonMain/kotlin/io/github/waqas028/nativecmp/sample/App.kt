package io.github.waqas028.nativecmp.sample

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.waqas028.nativecmp.NativeButton
import io.github.waqas028.nativecmp.NativeIconButton
import io.github.waqas028.nativecmp.NativeOutlinedButton
import io.github.waqas028.nativecmp.NativeTextButton
import io.github.waqas028.nativecmp.NativeTonalButton

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC6),
            tertiary = Color(0xFF3700B3)
        )
    ) {
        var count by remember { mutableStateOf(0) }
        
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Native-CMP-Kit") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5)),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    SummaryCard(count)
                }

                item {
                    SectionHeader("Standard Variants")
                }
                item {
                    ButtonGallerySection {
                        NativeButton(text = "Filled", onClick = { count++ })
                        NativeTonalButton(text = "Tonal", onClick = { count++ })
                        NativeOutlinedButton(text = "Outlined", onClick = { count++ })
                        NativeTextButton(text = "Text", onClick = { count++ })
                    }
                }

                item {
                    SectionHeader("Customization")
                }
                item {
                    ButtonGallerySection {
                        NativeButton(
                            text = "Custom Colors",
                            onClick = { count++ },
                            containerColor = Color(0xFFE91E63),
                            contentColor = Color.White
                        )
                        NativeButton(
                            text = "Rounded 24dp",
                            onClick = { count++ },
                            shape = RoundedCornerShape(24.dp)
                        )
                        NativeOutlinedButton(
                            text = "Thick Border",
                            onClick = { count++ },
                            border = BorderStroke(3.dp, Color(0xFF4CAF50))
                        )
                    }
                }

                item {
                    SectionHeader("States & Icons")
                }
                item {
                    ButtonGallerySection {
                        NativeButton(text = "Disabled", onClick = { }, enabled = false)
                        NativeIconButton(onClick = { count = 0 }) {
                            Icon(Icons.Default.Refresh, contentDescription = "Reset")
                        }
                    }
                }
                
                item {
                    Spacer(Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun SummaryCard(count: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Interactive Counter",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
            Text(
                text = "$count",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ButtonGallerySection(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        FlowRow(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            content()
        }
    }
}
