package de.check24.todo.ui

import androidx.compose.*
import androidx.ui.animation.Crossfade
import androidx.ui.material.DrawerState
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import de.check24.todo.ui.screens.LoginScreen
import de.check24.todo.ui.screens.MainContent
import de.check24.todo.ui.screens.OverviewScreen

@Model
object TodoApp {
    var currentScreen: Screen = Screen.Login
    val currentDrawerState: State<DrawerState> = +state { DrawerState.Closed }

    fun navigateTo(destination: Screen) {
        currentScreen = destination
    }

    sealed class Screen {
        object Overview : Screen()
        object Details : Screen()
        object Profile : Screen()
        object Settings : Screen()
        object Login : Screen()
    }
}

@Composable
fun TodoApp() {
    MaterialTheme(colors = lightThemeColors, typography = themeTypography) {
        Crossfade(TodoApp.currentScreen) { screen ->
            when (screen) {

                is TodoApp.Screen.Login -> LoginScreen()

                else -> MainContent(TodoApp.currentDrawerState) {
                    when (screen) {
                        is TodoApp.Screen.Overview -> OverviewScreen()
                        //TODO
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun TodoAppPreview() {
    TodoApp()
}



