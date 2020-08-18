package components.input

import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.joao.simsschool.R

object InputBindingAdapter {
    @JvmStatic @BindingAdapter(value = ["app:inputModel", "app:inputModelSubmit"], requireAll = false)
    fun setViewModel(view: InputView, inputModel: InputModel, onSubmit: (() -> Unit)?) {
        view.binding.inputModel = inputModel

        val edit = view.findViewById<EditText>(R.id.view_input)
        edit.addTextChangedListener(inputModel.getChangeTextWatcher())
        edit.onFocusChangeListener = inputModel.getOnFocusChangeListener()

        if(onSubmit != null) {
            edit.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    onSubmit()
                }
                false
            }
        }
    }
}