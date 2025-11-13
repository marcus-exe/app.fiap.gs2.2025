package com.techknowledgepills.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    onNavigateToContentList: () -> Unit,
    onNavigateToStress: () -> Unit,
    onNavigateToRecommendations: () -> Unit,
    onNavigateToContentDetail: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val latestStress by viewModel.latestStress.collectAsStateWithLifecycle()
    val recommendations by viewModel.recommendations.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Stress Indicator Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToStress() }
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Current Stress Level",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (latestStress != null) {
                    Text(
                        text = latestStress!!.stressLevel.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = viewModel.getStressLevelColor(latestStress!!.stressLevel)
                    )
                } else {
                    Text("No data available")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Recommendations Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recommended for You",
                style = MaterialTheme.typography.titleLarge
            )
            TextButton(onClick = onNavigateToRecommendations) {
                Text("See All")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recommendations.take(5)) { content ->
                    Card(
                        modifier = Modifier
                            .width(200.dp)
                            .height(150.dp)
                            .clickable { onNavigateToContentDetail(content.id) }
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Text(
                                text = content.title,
                                style = MaterialTheme.typography.titleSmall,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = content.type.name,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onNavigateToContentList,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Browse All Content")
        }
    }
}

