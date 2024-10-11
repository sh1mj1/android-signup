package nextstep.signup.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.domain.Email
import nextstep.signup.domain.EmailDomain
import nextstep.signup.domain.EmailId
import nextstep.signup.domain.Password
import nextstep.signup.domain.SignUp
import nextstep.signup.domain.UserName
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen() {
    var userName by remember { mutableStateOf(UserName("")) }
    var email by remember { mutableStateOf(Email(EmailId(""), EmailDomain("wooteco.com"))) }
    var password by remember {
        mutableStateOf(
            Password(
                "",
                ""
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignUpHeader(
            modifier = Modifier.wrapContentSize()
        )

        SignUpTextField(
            labelText = stringResource(R.string.sign_up_user_name_label),
            value = userName.name,
            onValueChange = {
                userName = UserName(it)
            },
            modifier = Modifier.fillMaxWidth()
        )

        SignUpTextField(
            labelText = stringResource(R.string.sign_up_email_label),
            value = email.whole(),
            onValueChange = {
                email = email.copy(
                    id = EmailId(it)
                )
            },
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        SignUpTextField(
            labelText = stringResource(R.string.sign_up_password_label),
            visualTransformation = PasswordVisualTransformation(),
            value = password.password,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                password = password.copy(
                    password = it
                )
            },
            keyboardType = KeyboardType.Password,
        )

        SignUpTextField(
            labelText = stringResource(R.string.sign_up_password_confirm_label),
            visualTransformation = PasswordVisualTransformation(),
            value = password.passwordConfirm,
            onValueChange = {
                password = password.copy(
                    passwordConfirm = it
                )
            },
            keyboardType = KeyboardType.Password,
            modifier = Modifier.fillMaxWidth()
        )

        SignUpButton(
            text = stringResource(R.string.sign_up_button),
            modifier = Modifier.fillMaxWidth(),
            enable = {
                SignUp(
                    userName = userName,
                    email = email,
                    password = password
                ).isValid()
            },
        )
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        SignUpScreen()
    }
}



