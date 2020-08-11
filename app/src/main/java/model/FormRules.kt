package model

enum class FormRulesNames {
    Email,
    MinLength,
    MaxLength
}

class FormRules {

    private fun isEmail(text: String): Boolean {
        return Regex("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}").matches(text)
    }

    private fun minLength(text: String, length: Int): Boolean {
        return text.length < length || (length == 0 && text == "")
    }

    private fun maxLength(text: String, length: Int): Boolean {
        return text.length > length
    }

    companion object {
        fun checkInputIsValid(input: FormInputModel): (FormRulesModel)? {
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