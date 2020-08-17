package components.input

import utils.FormRulesModel

class InputModel(
  val name: String,
  val hint: String,
  val keyboardType: Int,
  var value: String,
  val rules: Array<FormRulesModel> = arrayOf(),
  var validationRule: (FormRulesModel)? = null,
  var hasFocus: Boolean = false,
  var hasEverUnfocused: Boolean = false,
  var howManyAttempts: Int = 0
)