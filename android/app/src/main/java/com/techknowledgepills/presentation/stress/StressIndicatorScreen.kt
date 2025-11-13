package com.techknowledgepills.presentation.stress

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techknowledgepills.domain.model.StressLevel
import com.techknowledgepills.presentation.theme.*

@Composable
fun StressIndicatorScreen(
    onNavigateBack: () -> Unit,
    viewModel: StressIndicatorViewModel = hiltViewModel()
) {
    val stressIndicators by viewModel.stressIndicators.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Stress Indicators",
                style = MaterialTheme.typography.headlineLarge
            )
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.generateMockData() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generate Mock Data")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(stressIndicators) { indicator ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = indicator.stressLevel.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = getStressColor(indicator.stressLevel)
                                )
                                Text(
                                    text = indicator.timestamp,
                                    style = MaterialTheme.typography.bodySmall
                                )
                                indicator.notes?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getStressColor(stressLevel: StressLevel): Color {
    return when (stressLevel) {
        StressLevel.Low -> StressLow
        StressLevel.Medium -> StressMedium
        StressLevel.High -> StressHigh
        StressLevel.Critical -> StressCritical
    }
}

