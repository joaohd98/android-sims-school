package _backup.input

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import components.input.InputModel

class InputViewModel: ViewModel() {
    val input = MutableLiveData<InputModel>()
}