package screens.guest.login.view_model

import android.text.InputType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import components.input.InputModel

class LoginScreenViewModel: ViewModel() {
    val email: LiveData<InputModel> by lazy {
        MutableLiveData<InputModel>().apply {
            this.value = InputModel(
                name = "email",
                hint = "Email",
                keyboardType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                value = ""
            )
        }
    }

    val password: LiveData<InputModel> by lazy {
        MutableLiveData<InputModel>().apply {
            this.value = InputModel(
                name = "password",
                hint = "Password",
                keyboardType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD,
                value = ""
            )
        }
    }
}

