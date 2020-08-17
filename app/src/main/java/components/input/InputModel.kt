package components.input

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.joao.simsschool.R
import utils.FormRules
import utils.FormRulesModel
import androidx.databinding.library.baseAdapters.BR

class InputModel(
  private val _context: android.app.Application,
  private val _name: String,
  private val _hint: String,
  private val _keyboardType: Int,
  private var _value: String = "",
  private var _background: Drawable = ContextCompat.getDrawable(
    _context,
    R.drawable.view_input_border_default
  )!!,
  private var _rules: Array<FormRulesModel> = arrayOf(),
  private var _validationRule: (FormRulesModel)? = null,
  private var _hasFocus: Boolean = false,
  private var _hasEverUnfocused: Boolean = false,
  private var _howManyAttempts: Int = 0
) : BaseObservable() {

  var value: String
    @Bindable get() = _value
    set(value) {
      this._value = value
      notifyPropertyChanged(BR.value)
    }

  var background: Drawable
    @Bindable get() = _background
    set(value) {
      this._background = value
      notifyPropertyChanged(BR.background)
    }

  val keyboardType = _keyboardType
  val hint = _hint
  val rules = _rules

  fun getChangeTextWatcher(): TextWatcher {
    return object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
      override fun afterTextChanged(s: Editable) {}

      override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
      ) {
        value = s.toString()
        checkInput()
      }
    }
  }

  fun getOnFocusChangeListener(): View.OnFocusChangeListener {
    return View.OnFocusChangeListener { _, isFocused ->
      if (isFocused) {
        _hasFocus = true
      } else {
        _hasFocus = false
        _hasEverUnfocused = true
      }
      checkInput()
    }
  }

  private fun checkInput() {
    setValidationRule()
    setBackground()
  }

  private fun setValidationRule() {
    _validationRule = FormRules.checkInputIsValid(this)
  }

  private fun setBackground() {
    var color = R.drawable.view_input_border_default

    if((_hasEverUnfocused || _howManyAttempts > 0) && _validationRule != null) {
      color = if(_hasFocus) {
        R.drawable.view_input_border_warning
      } else {
        R.drawable.view_input_border_error
      }
    }

    else if((_value != "" || _howManyAttempts > 0) && _validationRule == null) {
      color = R.drawable.view_input_border_success
    }

    background = ContextCompat.getDrawable(_context, color)!!
  }

}
