package components

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.fragment_input.*
import model.FormInputModel

class InputFragment : Fragment() {
    var inputModel: (FormInputModel)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    fun setProps(input: FormInputModel) {
        inputModel = input

        inputModel?.let {
            fragment_input.setText(it.value)
            fragment_input.hint = it.hint
            fragment_input.inputType = it.keyboardType

            fragment_input.addTextChangedListener(it.getTextChangeListener(getLayoutInput()))
            fragment_input.onFocusChangeListener = it.getOnFocusChangeListener(getLayoutInput())
        }
    }

    private fun getLayoutInput(): (input: FormInputModel) -> Unit {
        return { input ->
            var color = R.color.colorPrimaryDark

            if ((input.hasEverUnfocused || input.howManyAttempts > 0) && input.validationRule != null) {
                color = if (input.hasFocus) {
                    R.color.yellow
                } else {
                    R.color.red
                }
            } else if ((input.value != "" || input.howManyAttempts > 0) && input.validationRule != null) {
                color = R.color.green
            }

            val drawable = fragment_input.background as GradientDrawable
            drawable.setStroke(3, ContextCompat.getColor(context!!, color))

            this.inputModel = input
        }
    }

}