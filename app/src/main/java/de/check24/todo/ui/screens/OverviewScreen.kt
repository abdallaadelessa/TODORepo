package de.check24.todo.ui.screens

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Dp
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.Image
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import de.check24.todo.DataProvider
import de.check24.todo.R
import de.check24.todo.pojo.SettingsState
import de.check24.todo.pojo.Todo
import de.check24.todo.ui.TodoApp
import de.check24.todo.ui.utils.imageUrl

/**
 * @author Created by Abdullah Essa on 07.02.20.
 */
@Composable
fun OverviewScreen(settingsState: SettingsState) {
    val cornerRadius = if (settingsState.roundCorners) {
        8.dp
    } else {
        0.dp
    }
    Stack {
        aligned(Alignment.TopCenter) {
            val todoList = DataProvider.todoList
            Column(modifier = Expanded) {
                VerticalScroller {
                    Column(modifier = Spacing(16.dp)) {
                        todoList.forEach {
                            TodoCard(it, cornerRadius)
                            HeightSpacer(height = 16.dp)
                        }
                    }
                }
            }
        }

        aligned(Alignment.BottomRight) {
            Padding(padding = EdgeInsets(0.dp, 12.dp, 12.dp, 12.dp)) {
                FloatingActionButton(
                        text = "+",
                        color = Color.White
                )
            }
        }
    }

}

@Composable
fun TodoCard(todo: Todo, cornerRadius: Dp) {
    Card(shape = RoundedCornerShape(cornerRadius), border = Border(Color.Gray, 0.2.dp)) {
        Ripple(bounded = true) {
            Clickable(onClick = {
                TodoApp.navigateTo(TodoApp.Screen.Details(todo))
            }) {
                Container(modifier = Height(280.dp)) {
                    Column(modifier = Expanded) {
                        val image = +imageUrl(todo.imageUrl) ?: +imageResource(R.drawable.header)
                        Container(modifier = Height(100.dp) wraps Expanded) {
                            DrawImage(image)
                        }
                        Column(modifier = Spacing(16.dp)) {
                            Text(
                                text = todo.title,
                                style = ((+MaterialTheme.typography()).h6).withOpacity(0.87f),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            HeightSpacer(height = 4.dp)
                            Text(
                                text = todo.description,
                                style = ((+MaterialTheme.typography()).body1).withOpacity(0.87f)
                            )
                            HeightSpacer(height = 2.dp)
                            Align(alignment = Alignment.BottomLeft) {
                                Text(
                                    text = "${todo.creationTime}",
                                    style = ((+MaterialTheme.typography()).body2).withOpacity(0.6f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun OverviewScreenPreview() {
    OverviewScreen(SettingsState())
}
