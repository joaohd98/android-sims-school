package components.input

import utils.FormRulesModel

data class InputModel(
  val name: String,
  val hint: String,
  val keyboardType: Int,
  var value: String,
  val rules: Array<FormRulesModel> = arrayOf(),
  var validationRule: (FormRulesModel)? = null,
  var hasFocus: Boolean = false,
  var hasEverUnfocused: Boolean = false,
  var howManyAttempts: Int = 0
) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as InputModel

    if (name != other.name) return false
    if (hint != other.hint) return false
    if (keyboardType != other.keyboardType) return false
    if (value != other.value) return false
    if (!rules.contentEquals(other.rules)) return false
    if (validationRule != other.validationRule) return false
    if (hasFocus != other.hasFocus) return false
    if (hasEverUnfocused != other.hasEverUnfocused) return false
    if (howManyAttempts != other.howManyAttempts) return false

    return true
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + hint.hashCode()
    result = 31 * result + keyboardType
    result = 31 * result + value.hashCode()
    result = 31 * result + rules.contentHashCode()
    result = 31 * result + (validationRule?.hashCode() ?: 0)
    result = 31 * result + hasFocus.hashCode()
    result = 31 * result + hasEverUnfocused.hashCode()
    result = 31 * result + howManyAttempts
    return result
  }


}