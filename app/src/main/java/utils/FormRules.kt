package utils

import components.input.InputModel


enum class FormRulesNames {
    Email,
    MinLength,
    MaxLength
}

class FormRulesModel(var name: FormRulesNames, var message: String, var optionalParam: (Any)? = null)

class FormRules {
    private fun isEmail(text: String): Boolean {
        return  !(text.isNotEmpty() and android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches());
    }

    private fun minLength(text: String, length: Int): Boolean {
        return text.length < length || (length == 0 && text == "")
    }

    private fun maxLength(text: String, length: Int): Boolean {
        return text.length > length
    }

    companion object {
        fun checkInputIsValid(input: InputModel): (FormRulesModel)? {
            val validations = FormRules()

            input.rules.forEach {
                when (it.name) {
                    FormRulesNames.Email -> {
                        if (validations.isEmail(input.value)) {
                            return it
                        }
                    }
                    FormRulesNames.MinLength -> {
                        val length = it.optionalParam as Int
                        if (validations.minLength(input.value, length)) {
                            return it
                        }
                    }
                    FormRulesNames.MaxLength -> {
                        val length = it.optionalParam as Int
                        if (validations.maxLength(input.value, length)) {
                            return it
                        }
                    }
                }
            }

            return null
        }

    }
}