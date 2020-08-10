package model

import android.text.InputType
import java.io.Serializable

class FormModel(
    val inputs: Array<FormInputModel>
    ) {
}

class FormInputModel (
    val name: String,
    val hint: String,
    var value: String
): Serializable {


//    @Published var name: String
//    @Published var placeholder: String
//    @Published var value: String
//    @Published var isPassword: Bool
//    @Published var keyboardReturnText: UIReturnKeyType
//    @Published var onKeyboardReturn: () -> Void
//    @Published var keyboardType: UIKeyboardType
//    @Published var rules: [FormRulesModel]
//    @Published var validationRule: (FormRulesModel)?
//    @Published var hasFocus: Bool
//    @Published var hasEverUnfocused: Bool
//    @Published var howManyAttempts: Int
//    @Published var becomeFirstResponder: () ->  Bool

}