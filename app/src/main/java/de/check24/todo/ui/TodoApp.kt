package de.check24.todo.ui

import androidx.compose.*
import androidx.ui.animation.Crossfade
import androidx.ui.layout.Expanded
import androidx.ui.material.DrawerState
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import de.check24.todo.pojo.SettingsState
import de.check24.todo.pojo.Todo
import de.check24.todo.ui.screens.*

@Model
object TodoApp {

    var currentScreen: Screen = Screen.Overview
        private set

    val currentDrawerState: State<DrawerState> = +state { DrawerState.Closed }

    fun navigateTo(destination: Screen) {
        currentScreen = destination
    }

    sealed class Screen {
        object Overview : Screen()
        object Create : Screen()
        class Details(val todo: Todo) : Screen()
        object Profile : Screen()
        object Settings : Screen()
        object Login : Screen()
    }
}

@Composable
fun TodoApp(settingsState: SettingsState) {
    MaterialTheme(colors = getColorPalette(settingsState.darkMode)) {

        Crossfade(TodoApp.currentScreen) { screen ->
            when (screen) {

                is TodoApp.Screen.Login -> wrapInsideSurface { LoginAppScreen(userInfo = UserInfo()) }

                is TodoApp.Screen.Create -> wrapInsideSurface { CreateScreen() }

                is TodoApp.Screen.Details -> wrapInsideSurface { DetailsScreen(screen.todo) }

                else -> MainContent(TodoApp.currentDrawerState) {
                    when (screen) {
                        is TodoApp.Screen.Overview -> OverviewScreen(settingsState)
                        is TodoApp.Screen.Create -> CreateScreen()
                        is TodoApp.Screen.Details -> DetailsScreen(screen.todo)
                        is TodoApp.Screen.Settings -> SettingsScreen(settingsState)
                    }
                }
            }
        }

    }
}

private fun wrapInsideSurface(content: @Composable() () -> Unit) {
    Surface(color = (+MaterialTheme.colors()).background, modifier = Expanded) {
        content()
    }
}

@Preview
@Composable
private fun TodoAppPreview() {
    TodoApp(SettingsState(darkMode = false))
}



