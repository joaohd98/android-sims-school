package screens.guest.login.model

import model.FormInputModel

class LoginScreenModel {
    var form = arrayOf(
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
}