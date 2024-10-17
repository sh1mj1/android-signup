package nextstep.signup.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.domain.Password
import nextstep.signup.domain.PasswordResult

@Composable
fun PasswordScreen(
    modifier: Modifier = Modifier,
    password: String = "",
    passwordConfirm: String = "",
    visualTransformation: VisualTransformation = PasswordVisualTransformation()
) {
    Column {
        SignUpTextField2(
            modifier = modifier,
            value = password,
            visualTransformation = visualTransformation,
            isError = when (Password.from(password, passwordConfirm)) {
                is PasswordResult.EmptyField -> false
                is PasswordResult.Failure -> true
                is PasswordResult.Success -> false
            },
            supportingText = {
                when (Password.from(password, passwordConfirm)) {
                    is PasswordResult.EmptyField -> return@SignUpTextField2
                    is PasswordResult.Success -> return@SignUpTextField2
                    is PasswordResult.InvalidPasswordLength -> Text(text = "비밀번호는 8~16자여야 합니다.")
                    is PasswordResult.InvalidPasswordFormat -> Text(text = "비밀번호는 영문과 숫자를 포함해야 합니다.")
                    else -> return@SignUpTextField2
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        SignUpTextField2(
            modifier = modifier,
            value = passwordConfirm,
            visualTransformation = visualTransformation,
            isError = when (Password.from(password, passwordConfirm)) {
                is PasswordResult.EmptyField -> false
                is PasswordResult.Failure -> true
                is PasswordResult.Success -> false
            },
            supportingText = {
                when (Password.from(password, passwordConfirm)) {
                    is PasswordResult.EmptyField -> return@SignUpTextField2
                    is PasswordResult.Success -> return@SignUpTextField2
                    is PasswordResult.NotSamePasswordConfirm -> Text(text = "비밀번호가 일치하지 않습니다.")
                    else -> return@SignUpTextField2
                }
            }
        )
    }
}

@Preview
@Composable
private fun PasswordTextFieldsPreview(@PreviewParameter(PasswordPreviewParameter::class) passwords: Pair<String, String>) {
    PasswordScreen(
        password = passwords.first,
        passwordConfirm = passwords.second
    )
}

class PasswordPreviewParameter : PreviewParameterProvider<Pair<String, String>> {
    override val values: Sequence<Pair<String, String>> = sequenceOf(
        // empty
        Pair("a", ""),
        Pair("1234567", ""),
        Pair("qwertyuio", ""),

        // invalid format
        Pair("a", "a"),
        Pair("12345678", "12345678"),
        Pair("qwertyuio", "qwertyuio"),

        // not same
        Pair("abcd1234", "abcd12345"),

        // success
        Pair("abcd1234", "abcd1234")
    )
}
