package components.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.joao.simsschool.R
import com.joao.simsschool.databinding.FragmentInputBinding

class Input: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentInputBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_input, container, false
        )

        return binding.root
    }


//    private fun getLayoutInput(): (input: FormInputModel) -> Unit {
//        return { input ->
//            var color = R.color.colorPrimaryDark
//
//            if ((input.hasEverUnfocused || input.howManyAttempts > 0) && input.validationRule != null) {
//                color = if (input.hasFocus) {
//                    R.color.yellow
//                } else {
//                    R.color.red
//                }
//            } else if ((input.value != "" || input.howManyAttempts > 0) && input.validationRule != null) {
//                color = R.color.green
//            }
//
//            val drawable = fragment_input.background as GradientDrawable
//            drawable.setStroke(3, ContextCompat.getColor(context!!, color))
//
//            this.inputModel = input
//        }
//    }

}