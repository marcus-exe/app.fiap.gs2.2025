package com.techknowledgepills.presentation.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techknowledgepills.domain.model.ContentType

@Composable
fun ContentDetailScreen(
    contentId: Int,
    onNavigateBack: () -> Unit,
    viewModel: ContentViewModel = hiltViewModel()
) {
    val content by viewModel.content.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    LaunchedEffect(contentId) {
        viewModel.loadContentById(contentId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = onNavigateBack) {
                Text("Back")
            }
        }

        if (isLoading) {
            CircularProgressIndicator()
        } else if (content != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = content!!.title,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Type: ${content!!.type.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))

                val currentContent = content!!
                val contentType: ContentType = currentContent.type
                when (contentType) {
                    ContentType.Article -> {
                        Text(
                            text = currentContent.body,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    ContentType.Video -> {
                        if (currentContent.videoUrl != null) {
                            Text(
                                text = "Video URL: ${currentContent.videoUrl}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "\nNote: In a production app, you would embed the video player here.",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                    ContentType.Quiz -> {
                        Text(
                            text = currentContent.body,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        if (currentContent.quizData != null) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Quiz Data: ${currentContent.quizData}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        } else {
            Text("Content not found")
        }
    }
}

