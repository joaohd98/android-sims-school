package screens.guest.login.model

import android.text.InputType
import model.FormInputModel
import model.FormModel

class LoginScreenModel {
    var form = FormModel(
        inputs = arrayOf(
            FormInputModel(
                name = "email",
                hint = "Email",
                keyboardType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                value = ""
            ),
            FormInputModel(
                name = "password",
                hint = "Password",
                keyboardType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD,
                value = ""
            )
        )
    )
}
