package de.check24.todo.pojo

import androidx.compose.Model
import java.util.*

data class User(val name: String, val email: String, val imgUrl: String, val birthday: Date)
data class Todo(
    val title: String,
    val description: String,
    val imageUrl: String = "https://miro.medium.com/max/1024/1*Er24qsvJdqLofK-sK0QzpA.jpeg",
    val creationTime: Date = Date()
)

@Model
class SettingsState(
    var darkMode: Boolean = false
)
