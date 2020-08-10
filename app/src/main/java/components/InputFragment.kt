package components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.joao.simsschool.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_input.*
import model.FormInputModel

class InputFragment : Fragment() {
    var formInputModel: FormInputModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        inputFragmentSimpleInput.hint = this.formInputModel?.hint
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.clearFindViewByIdCache()
    }

    fun setInputProps(formInputModel: FormInputModel) {
        this.formInputModel = formInputModel
    }

}