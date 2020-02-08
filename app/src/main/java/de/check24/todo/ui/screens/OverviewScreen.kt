package de.check24.todo.ui.screens

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview

/**
 * @author Created by Abdullah Essa on 07.02.20.
 */
@Composable
fun OverviewScreen() {
    Column {
        Container(modifier = Gravity.Center wraps ExpandedHeight wraps ExpandedWidth) {
            Text(
                text = "Overview Screen",
                style = (+MaterialTheme.typography()).body1
            )
        }
    }
}

@Preview
@Composable
private fun OverviewScreenPreview() {
    OverviewScreen()
}
