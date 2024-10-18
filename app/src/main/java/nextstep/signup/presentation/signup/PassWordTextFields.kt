package nextstep.signup.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.domain.Password3
import nextstep.signup.domain.PasswordResult3

@Composable
fun PasswordTextFields(
    modifier: Modifier = Modifier,
    passwordLabelText: String = stringResource(R.string.default_text_field_label),
    passwordConfirmLabelText: String = stringResource(R.string.default_text_field_label),
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    password: String = "",
    passwordConfirm: String = ""
) {
    Column {
        SignUpTextField2(
            modifier = modifier,
            labelText = passwordLabelText,
            value = password,
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            isError = when (Password3.from(password, passwordConfirm)) {
                is PasswordResult3.EmptyField -> false
                is PasswordResult3.Failure -> true
                is PasswordResult3.Success -> false
            },
            supportingText = {
                when (Password3.from(password, passwordConfirm)) {
                    is PasswordResult3.EmptyField -> return@SignUpTextField2
                    is PasswordResult3.Success -> return@SignUpTextField2
                    is PasswordResult3.InvalidPasswordLength -> Text(text = "비밀번호는 8~16자여야 합니다.")
                    is PasswordResult3.InvalidPasswordFormat -> Text(text = "비밀번호는 영문과 숫자를 포함해야 합니다.")
                    else -> return@SignUpTextField2
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        SignUpTextField2(
            modifier = modifier,
            labelText = passwordConfirmLabelText,
            value = passwordConfirm,
            onValueChange = onPasswordConfirmChange,
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            isError = when (Password3.from(password, passwordConfirm)) {
                is PasswordResult3.EmptyField -> false
                is PasswordResult3.Failure -> true
                is PasswordResult3.Success -> false
            },
            supportingText = {
                when (Password3.from(password, passwordConfirm)) {
                    is PasswordResult3.EmptyField -> return@SignUpTextField2
                    is PasswordResult3.Success -> return@SignUpTextField2
                    is PasswordResult3.NotSamePasswordConfirm -> Text(text = "비밀번호가 일치하지 않습니다.")
                    else -> return@SignUpTextField2
                }
            }
        )
    }
}

@Preview
@Composable
private fun PasswordTextFieldsPreview(@PreviewParameter(PasswordPreviewParameter3::class) passwords: Pair<String, String>) {
    PasswordTextFields(
        password = passwords.first,
        passwordConfirm = passwords.second,
        onPasswordChange = {},
        onPasswordConfirmChange = {}
    )
}

class PasswordPreviewParameter3 : PreviewParameterProvider<Pair<String, String>> {
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
