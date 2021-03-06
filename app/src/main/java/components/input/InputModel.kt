package components.input

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.joao.simsschool.R
import utils.FormRules
import utils.FormRulesModel

class InputModel(
  private val _context: android.app.Application,
  private val _hint: String,
  private val _keyboardType: Int,
  private var _value: String = "",
  private val _rules: Array<FormRulesModel> = arrayOf(),
  private var _validationRule: (FormRulesModel)? = null,
  private var _hasFocus: Boolean = false,
  private var _hasEverUnfocused: Boolean = false
) : BaseObservable() {

  var value: String
    @Bindable get() = _value
    set(value) {
      this._value = value
      notifyPropertyChanged(BR.value)
    }

  var background: Drawable = ContextCompat.getDrawable(_context, R.drawable.view_input_border_default)!!
    @Bindable get
    set(value) {
      field = value
      notifyPropertyChanged(BR.background)
    }

  var message: String = ""
    @Bindable get
    set(value) {
      field = value
      notifyPropertyChanged(BR.message)
    }


  var messageColor: Int = 0
    @Bindable get
    set(value) {
      field = value
      notifyPropertyChanged(BR.messageColor)
    }

  fun isValid() = _validationRule == null
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

  fun checkInput(isSubmitting: Boolean = false) {
    if (isSubmitting) {
      _hasEverUnfocused = true
    }

    setValidationRule()
    setInputColor()
  }

  private fun setValidationRule() {
    _validationRule = FormRules.checkInputIsValid(this)
  }

  private fun setInputColor() {
    var background = R.drawable.view_input_border_default
    var message = ""
    var messageColor = 0

    if(_hasEverUnfocused && _validationRule != null) {
      message = _validationRule!!.message

      if(_hasFocus) {
        background = R.drawable.view_input_border_warning
        messageColor = R.color.yellow
      } else {
        background = R.drawable.view_input_border_error
        messageColor = R.color.red
      }
    }

    else if(_value != "" && _validationRule == null) {
      background = R.drawable.view_input_border_success
    }
    
    this.message = message
    this.messageColor = if(messageColor != 0) ContextCompat.getColor(_context, messageColor) else 0
    this.background = ContextCompat.getDrawable(_context, background)!!
  }


}
