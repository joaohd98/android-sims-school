package screens.guest.login.view_model

import android.text.InputType
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import components.input.InputModel

class LoginScreenViewModel(application: android.app.Application) : AndroidViewModel(application) {
    private val context = application
    val email: LiveData<InputModel> by lazy {
        MutableLiveData<InputModel>().apply {
            this.value = InputModel(
                name = "email",
                hint = "Email",
                keyboardType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                value = "teste@mail.com"
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


    fun onSubmit() {
        Toast.makeText(context, "${email.value?.value} ${password.value?.value}", Toast.LENGTH_SHORT).show()
    }
}

