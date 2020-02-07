package de.check24.todo.pojo

import android.accounts.AuthenticatorDescription
import java.util.*

data class User(val name: String, val email: String, val imgUrl: String, val birthday: Date)
data class Todo(
    val title: String,
    val description: String,
    val imageUrl: String,
    val crationtime: Date
)