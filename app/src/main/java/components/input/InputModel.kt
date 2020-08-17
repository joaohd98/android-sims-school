package components.input

import android.graphics.Color
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
  private val _rules: Array<FormRulesModel> = arrayOf(),
  private var _validationRule: (FormRulesModel)? = null,
  private var _hasFocus: Boolean = false,
  private var _hasEverUnfocused: Boolean = false,
  private var _howManyAttempts: Int = 0,
  private var _message: String = "",
  private var _messageColor: Int = ContextCompat.getColor(
    _context,
    R.color.borderDefault
  )
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

  var message: String
    @Bindable get() = _message
    set(value) {
      _message = value
      notifyPropertyChanged(BR.message)
    }


  var messageColor: Int
    @Bindable get() = _messageColor
    set(value) {
      _messageColor = value
      notifyPropertyChanged(BR.messageColor)
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
    setInputColor()
  }

  private fun setValidationRule() {
    _validationRule = FormRules.checkInputIsValid(this)
  }

  private fun setInputColor() {
    var background = R.drawable.view_input_border_default
    var message = ""
    var messageColor = R.color.borderDefault

    if((_hasEverUnfocused || _howManyAttempts > 0) && _validationRule != null) {
      message = _validationRule!!.message

      if(_hasFocus) {
        background = R.drawable.view_input_border_warning
        messageColor = R.color.yellow
      } else {
        background = R.drawable.view_input_border_error
        messageColor = R.color.red
      }
    }

    else if((_value != "" || _howManyAttempts > 0) && _validationRule == null) {
      background = R.drawable.view_input_border_success
    }
    
    this.message = message
    this.messageColor = ContextCompat.getColor(_context, messageColor)
    this.background = ContextCompat.getDrawable(_context, background)!!
  }



}
