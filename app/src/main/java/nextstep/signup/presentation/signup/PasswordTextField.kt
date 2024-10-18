package nextstep.signup.presentation.signup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.domain.Password
import nextstep.signup.domain.Password3
import nextstep.signup.domain.PasswordResult

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    onValueChange: (String) -> Unit,
    labelText: String = stringResource(R.string.default_text_field_label)
) {
    SignUpTextField2(
        modifier = modifier,
        labelText = labelText,
        value = password,
        onValueChange = onValueChange,
        isError = when (Password.from(password)) {
            is PasswordResult.EmptyField -> false
            is PasswordResult.Success -> false
            is PasswordResult.Failure -> true
        },
        supportingText = {
            when (Password.from(password)) {
                is PasswordResult.EmptyField -> return@SignUpTextField2
                is PasswordResult.Success -> return@SignUpTextField2
                is PasswordResult.InvalidPasswordLength ->
                    Text(text = stringResource(id = R.string.error_message_password_invalid_length))

                is PasswordResult.InvalidPasswordFormat ->
                    Text(text = stringResource(id = R.string.error_message_password_invalid_format))
            }
        }
    )
}

@Preview
@Composable
fun PasswordTextFieldPreview(
    @PreviewParameter(PasswordPreviewParameter::class) password: String
) {
    PasswordTextField(
        password = password,
        onValueChange = {},
        labelText = stringResource(id = R.string.sign_up_password_label)
    )
}

class PasswordPreviewParameter : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        // length error
        "a",
        "1234567",

        // format error
        "qwertyuio",
        "12345678",
        "qwertyuio",

        // success
        "abcd1234",
    )
}
