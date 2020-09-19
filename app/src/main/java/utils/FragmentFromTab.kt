package utils

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class FragmentFromTab: Fragment() {
    abstract val viewModel: ViewModelFromTab

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListenerToInit()
    }

    private fun setListenerToInit() {
        viewModel.hasInit.observe(viewLifecycleOwner, {
            if(it) {
                initMethod()
            }
        })
    }

    abstract fun initMethod()

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)

        val hasInit = viewModel.hasInit.value!!

        if(menuVisible && !hasInit) {
            viewModel.hasInit.value = true
        }
    }
}