package nextstep.signup.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen() {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmed by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignUpHeader()

        SignUpTextField(
            labelText = "Username",
            value = userName,
            onValueChange = { userName = it },
        )

        SignUpTextField(
            labelText = "Email",
            value = email,
            onValueChange = { email = it },
            keyboardType = KeyboardType.Email,
        )

        SignUpTextField(
            labelText = "Password",
            visualTransformation = PasswordVisualTransformation(),
            value = password,
            onValueChange = { password = it },
            keyboardType = KeyboardType.Password,
        )

        SignUpTextField(
            labelText = "Password Confirm",
            visualTransformation = PasswordVisualTransformation(),
            value = passwordConfirmed,
            onValueChange = { passwordConfirmed = it },
            keyboardType = KeyboardType.Password,
        )

        SignUpButton(
            enable = {
                notEmpty(email, userName, password, passwordConfirmed) && password == passwordConfirmed
            },
        )
    }
}

private fun notEmpty(
    email: String,
    userName: String,
    password: String,
    passwordConfirmed: String
) = email.isNotEmpty() && userName.isNotEmpty() &&
        password.isNotEmpty() && passwordConfirmed.isNotEmpty()


@Preview
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}



