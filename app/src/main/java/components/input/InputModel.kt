package components.input

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import utils.FormRulesModel

class InputModel(
  val name: String,
  val hint: String,
  val keyboardType: Int,
  var value: String = "",
  val rules: Array<FormRulesModel> = arrayOf(),
  var validationRule: (FormRulesModel)? = null,
  var hasFocus: Boolean = false,
  var hasEverUnfocused: Boolean = false,
  var howManyAttempts: Int = 0
): BaseObservable() {

  fun getChangeTextWatcher(): TextWatcher? {
    return object : TextWatcher {
      override fun beforeTextChanged(
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
      ) {

      }

      override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
      ) {
        value = s.toString()
      }

      override fun afterTextChanged(s: Editable) {
        // Do nothing.
      }
    }
  }
}