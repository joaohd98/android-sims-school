package model

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.joao.simsschool.R
import java.io.Serializable

class FormModel(
    val inputs: Array<FormInputModel>
)

class FormInputModel(
    val name: String,
    val hint: String,
    val keyboardType: Int,
    var value: String,
    val rules: Array<FormRulesModel> = arrayOf(),
    var validationRule: (FormRulesModel)? = null,
    var hasFocus: Boolean = false,
    var hasEverUnfocused: Boolean = false,
    var howManyAttempts: Int = 0
): Serializable {

    fun getTextChangeListener(onCallBack: (input: FormInputModel) -> Unit): TextWatcher {
        val that = this

        return (object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                that.value = s.toString()
                that.validationRule = FormRules.checkInputIsValid(that)
                onCallBack(that)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }

        })
    }

    fun getOnFocusChangeListener(onCallBack: (input: FormInputModel) -> Unit): View.OnFocusChangeListener {
        return View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                this.hasFocus = true
            } else {
                this.hasFocus = false
                this.hasEverUnfocused = true
                this.validationRule = FormRules.checkInputIsValid(this)
            }
            onCallBack(this)
        }
    }



}

class FormRulesModel(var name: FormRulesNames, var message: String, var optionalParam: (Any)? = null)


//    @Published var onKeyboardReturn: () -> Void
//    @Published var rules: [FormRulesModel]
//    @Published var validationRule: (FormRulesModel)?
//    @Published var hasFocus: Bool
//    @Published var hasEverUnfocused: Bool
//    @Published var howManyAttempts: Int
//    @Published var becomeFirstResponder: () ->  Bool
