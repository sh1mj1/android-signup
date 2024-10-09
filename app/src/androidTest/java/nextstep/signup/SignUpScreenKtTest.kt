package nextstep.signup

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SignUpScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun signUpHeaderText() {
        // given
        composeTestRule.setContent {
            SignUpHeader()
        }

        // then
        composeTestRule
            .onNodeWithText("Welcome to Compose 🚀")
            .assertExists()
    }


    @Test
    fun signUpTextField() {
        // given
        composeTestRule.setContent {
            SignUpTextField(
                modifier = Modifier.testTag("text_field")
            )
        }

        // when
        composeTestRule.onNodeWithTag("text_field")
            .performTextInput("sh1mj1")

        // then
        composeTestRule.onNodeWithTag("text_field")
            .assert(hasText("sh1mj1"))
    }


}