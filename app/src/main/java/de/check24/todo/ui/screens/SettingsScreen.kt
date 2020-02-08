package de.check24.todo.ui.screens

import android.content.Context.MODE_PRIVATE
import androidx.compose.*
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Switch
import androidx.ui.tooling.preview.Preview
import de.check24.todo.MainActivity.Companion.SETTING_DARK_MODE
import de.check24.todo.pojo.SettingsState


@Composable
fun SettingsScreen(settingsState: SettingsState) {
    Column() {
        DarkModeSetting(settingsState)
    }
}

@Composable
private fun DarkModeSetting(settingsState: SettingsState) {
    val context = +ambient(ContextAmbient)
    val sharedPref = context.getSharedPreferences("settings", MODE_PRIVATE)
    Row(modifier = Spacing(left = 16.dp, right = 16.dp) wraps ExpandedHeight wraps ExpandedWidth) {
        Text(
            text = "Dark mode".toUpperCase(),
            modifier = Flexible(1f) wraps Gravity.Top wraps Spacing(16.dp),
            style = (+MaterialTheme.typography()).subtitle1
        )

        Container(modifier = Spacing(16.dp) wraps Gravity.Top) {
            Switch(
                checked = settingsState.darkMode,
                onCheckedChange = { newState ->
                    settingsState.darkMode = newState
                    sharedPref.edit().putBoolean(SETTING_DARK_MODE, newState).apply()
                })
        }
    }
}


@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(SettingsState(darkMode = false))
}
