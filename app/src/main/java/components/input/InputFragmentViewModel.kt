package components.input

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InputFragmentViewModel: ViewModel() {
    val input = MutableLiveData<InputFragmentModel>()
}