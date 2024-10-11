package nextstep.signup.domain

import android.util.Log

data class SignUp(
    val userName: UserName,
    val email: Email,
    val password: Password,
) {
    init {
        Log.d(TAG, "init: created")
    }
    constructor(
        userName: String,
        emailId: String,
        emailDomain: String,
        password: String,
        passwordConfirm: String
    ) : this(
        UserName(userName),
        Email(EmailId(emailId), EmailDomain(emailDomain)),
        Password(password, passwordConfirm)
    )

    fun isValid(): Boolean = userName.isValid() && email.isValid() && password.isValid()
}

private const val TAG = "SignUp"
