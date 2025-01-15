package com.mf.homeassignment4.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.mf.homeassignment4.data.models.SearchResultModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(resultViewModel: SearchResultModel, onDismiss: ()-> Unit, isDialogOpen: Boolean) {
    if(isDialogOpen) {
        val columnScrollState = rememberScrollState()
        val publishedDate = resultViewModel.published.orEmpty()
        var formattedPublishedDate = ""
        if (publishedDate.isNotEmpty()) {
            val zonedDateTime = ZonedDateTime.parse(publishedDate)
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")
            formattedPublishedDate = zonedDateTime.format(formatter)
        }
        BasicAlertDialog(onDismissRequest = { onDismiss }, properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true, )) {
            Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth(1.0f)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.verticalScroll(columnScrollState)) {
                    AsyncImage(
                        model = resultViewModel.media.m.orEmpty(),
                        contentDescription = resultViewModel.description.orEmpty(),
                        modifier = Modifier.width(200.dp).height(200.dp)
                    )
                    Text(resultViewModel.title.orEmpty())
                    Text(resultViewModel.description.orEmpty())
                    Text("Created by ${resultViewModel.author.orEmpty()}")
                    Text("Created at ${formattedPublishedDate}")
                    IconButton(onClick = {onDismiss()},) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close",
                            tint = Color.Black // Optional: Set the icon color
                        )
                    }
                }
            }
        }
    }
}