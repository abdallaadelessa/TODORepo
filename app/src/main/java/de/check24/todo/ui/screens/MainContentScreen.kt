package de.check24.todo.ui.screens

import androidx.compose.Composable
import androidx.compose.State
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import de.check24.todo.R
import de.check24.todo.ui.TodoApp
import de.check24.todo.ui.utils.VectorImageButton

//region Content

@Composable
fun MainContent(drawerState: State<DrawerState>, content: @Composable() () -> Unit) {
    val (drawerStateInternal, onDrawerStateChange) = drawerState
    ModalDrawerLayout(
        drawerState = drawerStateInternal,
        onStateChange = onDrawerStateChange,
        gesturesEnabled = drawerStateInternal == DrawerState.Opened,
        drawerContent = {
            DrawerMenu(
                currentScreen = TodoApp.currentScreen,
                closeDrawer = { onDrawerStateChange(DrawerState.Closed) }
            )
        },
        bodyContent = { DrawerContent(content) { onDrawerStateChange(DrawerState.Opened) } }
    )
}

@Preview
@Composable
private fun MainContentPreview() {
    MainContent(+state { DrawerState.Closed }) {
        OverviewScreen()
    }
}

//endregion

//region Drawer Components

@Composable
private fun AppBar(openDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Todo Overview") },
        navigationIcon = {
            VectorImageButton(R.drawable.ic_drawer_menu) {
                openDrawer()
            }
        }
    )
}

@Composable
private fun DrawerContent(content: @Composable() () -> Unit, openDrawer: () -> Unit) {
    Crossfade(TodoApp.currentScreen) { screen ->
        Column {
            AppBar(openDrawer)
            Surface(color = (+MaterialTheme.colors()).background) {
                content()
            }
        }
    }
}

@Composable
private fun DrawerMenu(currentScreen: TodoApp.Screen, closeDrawer: () -> Unit) {
    Column(modifier = Expanded) {

        HeightSpacer(24.dp)

        DrawerButton(
            label = "Overview",
            isSelected = currentScreen == TodoApp.Screen.Overview
        ) {
            TodoApp.navigateTo(TodoApp.Screen.Overview)
            closeDrawer()
        }

        HeightSpacer(24.dp)

        DrawerButton(
            label = "Settings",
            isSelected = currentScreen == TodoApp.Screen.Settings
        ) {
            TodoApp.navigateTo(TodoApp.Screen.Settings)
            closeDrawer()
        }

        HeightSpacer(24.dp)

        DrawerButton(
            label = "Details",
            isSelected = currentScreen == TodoApp.Screen.Details
        ) {
            TodoApp.navigateTo(TodoApp.Screen.Details)
            closeDrawer()
        }
    }
}

@Composable
private fun DrawerButton(
    modifier: Modifier = Modifier.None,
    label: String,
    isSelected: Boolean,
    action: () -> Unit
) {
    val colors = +MaterialTheme.colors()
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        colors.surface
    }

    Surface(
        modifier = modifier wraps Spacing(
            left = 8.dp,
            top = 8.dp,
            right = 8.dp
        ),
        color = backgroundColor,
        shape = RoundedCornerShape(4.dp)
    ) {
        Button(onClick = action, style = TextButtonStyle()) {
            Row(arrangement = Arrangement.Begin) {
                Text(
                    text = label,
                    style = (+MaterialTheme.typography()).body2.copy(
                        color = textIconColor
                    ),
                    modifier = ExpandedWidth
                )
            }
        }
    }
}

//endregion

