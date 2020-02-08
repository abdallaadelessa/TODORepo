package de.check24.todo.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.ui.core.*
import androidx.ui.foundation.shape.border.Border
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import de.check24.todo.ui.TodoApp

@Model
class UserInfo(
        var userLoggedIn: Boolean = false,
        var userName: String = "",
        var showError: Boolean = false
)

private lateinit var auth: FirebaseAuth
val context = ambient(ContextAmbient)

@Composable
fun LoginAppScreen(userInfo: UserInfo) {
    FirebaseApp.initializeApp(context.unaryPlus())
    auth = FirebaseAuth.getInstance()
    if (!userInfo.userLoggedIn) {
        LoginScreen(userInfo)
    } else {
        UserInfoScreen(userInfo)
    }
}

@Composable
fun UserInfoScreen(userInfo: UserInfo) {
    Column(modifier = Spacing(8.dp)) {
        Text(
                text = "Username: ${userInfo.userName}",
                modifier = Spacing(8.dp),
                style = (+MaterialTheme.typography()).h6
        )

    }
}

@Composable
fun LoginScreen(userInfo: UserInfo = UserInfo()) {
    Column {
        val userNameState = +state { "uran.ilazi@check24.de" }
        val showErrorState = +state { true }
        Text(
                text = "Username:",
                modifier = Spacing(8.dp),
                style = (+MaterialTheme.typography()).h4
        )
        Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
            Padding(8.dp) {
                TextField(
                        value = userNameState.value,
                        onValueChange = {
                            userNameState.value = it
                            userInfo.showError = false
                        }
                )

            }
        }

        val passwordState = +state { "" }
        Text(
                text = "Password:",
                modifier = Spacing(8.dp),
                style = (+MaterialTheme.typography()).h4
        )
        Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
            Padding(padding = 8.dp) {
                PasswordTextField(
                        value = passwordState.value,
                        onValueChange = {
                            passwordState.value = it
                            userInfo.showError = false
                        }
                )
            }
        }

        if ((userNameState.value.isNotEmpty()
                        && passwordState.value.isNotEmpty())
        ) {
            Row(arrangement = Arrangement.End) {
                Button(
                        text = "Login",
                        modifier = Spacing(8.dp),
                        onClick = {
                            println("Logged in!")
                            showErrorState.value = false
                            userInfo.userName = userNameState.value
                            userInfo.showError = true
                            auth.signInWithEmailAndPassword(userNameState.value, passwordState.value)
                                    .addOnCompleteListener {
                                        if (it.isSuccessful) {
                                            TodoApp.navigateTo(TodoApp.Screen.Overview)
                                        } else {
                                            userInfo.showError = true
                                            passwordState.value = ""
                                        }
                                    }
                                    .addOnFailureListener {
                                        println(it)
                                    }
                        }
                )
            }
        } else {
            var style = ((+MaterialTheme.typography()).h6)
            var text = "Please enter both username and password!"
            if (userInfo.showError) {
                style = style.copy(Color.Red)
                text = "Wrong username or password"
            }
            Text(
                    text = text,
                    modifier = Spacing(8.dp),
                    style = style
            )

        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        LoginScreen()
    }
}