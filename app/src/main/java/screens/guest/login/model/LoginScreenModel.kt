package screens.guest.login.model

import model.FormInputModel
import model.FormModel
import java.io.Serializable

class LoginScreenModel {
    var form = FormModel(
        inputs = arrayOf(
            FormInputModel(
                name = "email",
                hint = "Email",
                value = ""
            ),
            FormInputModel(
                name = "password",
                hint = "Password",
                value = ""
            )
        )
    )
}
