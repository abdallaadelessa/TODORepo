package de.check24.todo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.material.DrawerState
import de.check24.todo.pojo.SettingsState
import de.check24.todo.ui.TodoApp


class MainActivity : AppCompatActivity() {
    companion object {
        const val SETTING_DARK_MODE = "darkModeSettingKey"
    }

    lateinit var sharedPref: SharedPreferences
    lateinit var settingsState: SettingsState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE)
        settingsState = SettingsState(darkMode = sharedPref.getBoolean(SETTING_DARK_MODE, false))
        setContent {
            TodoApp(settingsState)
        }
    }

    override fun onBackPressed() {
        if(TodoApp.currentDrawerState.value == DrawerState.Opened){
            TodoApp.currentDrawerState.component2().invoke(DrawerState.Closed)
        }else{
            super.onBackPressed()
        }
    }
}
