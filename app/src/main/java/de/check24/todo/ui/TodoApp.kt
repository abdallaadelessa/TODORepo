package de.check24.todo.ui

import androidx.compose.*
import androidx.compose.frames.ModelList
import androidx.ui.animation.Crossfade
import androidx.ui.material.DrawerState
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import de.check24.todo.data.TodoModel
import de.check24.todo.data.UserModel
import de.check24.todo.ui.screens.LoginScreen
import de.check24.todo.ui.screens.MainContent
import de.check24.todo.ui.screens.OverviewScreen

@Model
object TodoApp {
    var currentScreen: Screen = Screen.Login
    val todoList = ModelList<TodoModel>()
    var user: UserModel? = null

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
                else -> {

                    val drawerState: State<DrawerState> = +state { DrawerState.Closed }

                    MainContent(drawerState) {

                        when (screen) {
                            is TodoApp.Screen.Overview -> OverviewScreen()
                            //TODO
                        }

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



