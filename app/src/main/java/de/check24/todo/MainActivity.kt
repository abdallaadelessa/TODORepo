package de.check24.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.material.DrawerState
import de.check24.todo.ui.TodoApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp()
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
