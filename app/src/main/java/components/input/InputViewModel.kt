package components.input

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InputViewModel: ViewModel() {
    val input = MutableLiveData<InputModel>()
}