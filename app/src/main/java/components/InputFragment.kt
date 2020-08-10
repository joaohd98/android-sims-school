package components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.joao.simsschool.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_input.*
import model.FormInputModel

private const val argFormInputModel = "argFormInputModel"

class InputFragment : Fragment() {
    companion object {
        fun getParams(param: FormInputModel): Bundle {
            val bundle = Bundle()

            bundle.putSerializable(argFormInputModel, param)

            return bundle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    fun setProps(input: FormInputModel) {
        fragment_input.setText(input.value)
        fragment_input.hint = input.hint
        fragment_input.inputType = input.keyboardType

    }

}