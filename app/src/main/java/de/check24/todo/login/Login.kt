package de.check24.todo.login

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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

@Model
class UserInfo(
    var userLoggedIn: Boolean = false,
    var userName: String = ""
)

private lateinit var auth: FirebaseAuth
val context = ambient(ContextAmbient)
class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContent {
            MaterialTheme {
                AppScreen(UserInfo())
            }
        }
    }
}

@Composable
fun AppScreen(userInfo: UserInfo) {
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
        val userNameState = +state { "" }
        Text(
            text = "Username:",
            modifier = Spacing(8.dp),
            style = (+MaterialTheme.typography()).h4
        )
        Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
            Padding(8.dp) {
                TextField(
                    value = userNameState.value,
                    onValueChange = { userNameState.value = it }
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
                    onValueChange = { passwordState.value = it }
                )
            }
        }

        if (userNameState.value.isNotEmpty()
            && passwordState.value.isNotEmpty()
        ) {
            Row(arrangement = Arrangement.End) {
                Button(
                    text = "Login",
                    modifier = Spacing(8.dp),
                    onClick = {
                        println("Logged in!")
                        userInfo.userName = userNameState.value
                        userInfo.userLoggedIn = true
                        auth.signInWithEmailAndPassword(userNameState.value, passwordState.value)
                            .addOnCompleteListener {

                            }
                    }
                )
            }
        } else {
            Text(
                text = "Please enter both username and password!",
                modifier = Spacing(8.dp),
                style = (+MaterialTheme.typography()).h6
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