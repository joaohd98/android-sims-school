package screens.guest.login.view_model

import android.text.InputType
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import components.input.InputModel
import utils.FormRulesModel
import utils.FormRulesNames

class LoginScreenViewModel(application: android.app.Application) : AndroidViewModel(application) {
    private val context = application
    val email: LiveData<InputModel> by lazy {
        MutableLiveData<InputModel>().apply {
            this.value = InputModel(
                _context = application,
                _hint = "Email",
                _keyboardType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                _value = "teste@mail.com",
                _rules = arrayOf(
                    FormRulesModel(FormRulesNames.Email, "Type a valid email address")
                )
            )
        }
    }
    val password: LiveData<InputModel> by lazy {
        MutableLiveData<InputModel>().apply {
            this.value = InputModel(
                _context = application,
                _hint = "Password",
                _keyboardType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD,
                _rules = arrayOf(
                    FormRulesModel(FormRulesNames.MinLength, "Password can't be empty", 0),
                    FormRulesModel(FormRulesNames.MaxLength, "Password can't have more than 8 digits", 8)
                )
            )
        }
    }


    fun onSubmit() {
        Toast.makeText(context, "${email.value?.value} ${password.value?.value}", Toast.LENGTH_SHORT).show()
    }
}

