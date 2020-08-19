package screens.guest.login.view_model

import android.text.InputType
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.*
import com.joao.simsschool.R
import components.input.InputModel
import components.input.InputView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import repositories.RepositoryStatus
import repositories.user.UserRequest
import repositories.user.UserRepository
import utils.FormRulesModel
import utils.FormRulesNames

class LoginScreenViewModel(application: android.app.Application): AndroidViewModel(application) {
    private val context = application
    private val userRepository = UserRepository

    val status: MutableLiveData<RepositoryStatus> by lazy {
        MutableLiveData<RepositoryStatus>(RepositoryStatus.NONE)
    }
    val statusMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val email: LiveData<InputModel> by lazy {
        MutableLiveData<InputModel>().apply {
            this.value = InputModel(
                _context = application,
                _hint = "Email",
                _value = "teste@mail.com",
                _keyboardType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
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
                _value = "abc123",
                _keyboardType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD,
                _rules = arrayOf(
                    FormRulesModel(FormRulesNames.MinLength, "Password can't be empty", 0),
                    FormRulesModel(FormRulesNames.MaxLength, "Password can't have more than 8 digits", 8)
                )
            )
        }
    }

    val hasTriedSubmitEmailInvalid: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val hasTriedSubmitPasswordInvalid: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }



    fun onSubmit() {
        val email = email.value!!
        val password = password.value!!

        email.checkInput(true)
        password.checkInput(true)

        if(!email.isValid()) {
            if(hasTriedSubmitEmailInvalid.value == null) {
                hasTriedSubmitEmailInvalid.value = true
            }
            else {
                hasTriedSubmitEmailInvalid.value = !(hasTriedSubmitEmailInvalid.value!!)
            }

            return
        }

        if (!password.isValid()) {
            if(hasTriedSubmitPasswordInvalid.value == null) {
                hasTriedSubmitPasswordInvalid.value = true
            }
            else {
                hasTriedSubmitPasswordInvalid.value = !(hasTriedSubmitPasswordInvalid.value!!)
            }

            return
        }

        status.value = RepositoryStatus.LOADING

        userRepository.signIn(
            object: UserRequest {
                override val email = email.value
                override val password = password.value
            },
            onSuccess = {
                status.value = RepositoryStatus.SUCCESS
            },
            onError = {
                statusMessage.value = when(it) {
                    is FirebaseAuthInvalidUserException -> "The email you typed doesn't belong to any account."
                    is FirebaseAuthInvalidCredentialsException -> "The password you typed isn't correct"
                    else -> "There was an internal error."
                }.plus("\nPlease try again later.")
                status.value = RepositoryStatus.FAILED
            }
        )

    }

    fun changedHasTriedSubmit(isValid: Boolean?, view: InputView) {
        if (isValid == null) {
            return
        }

        val edit = view.findViewById<EditText>(R.id.view_input)
        val animShake = AnimationUtils.loadAnimation(context, R.anim.shake_effect)

        edit.isFocusableInTouchMode = true
        edit.requestFocus()
        edit.startAnimation(animShake)
    }


}

