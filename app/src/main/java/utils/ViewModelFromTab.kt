package utils

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

abstract class ViewModelFromTab(application: android.app.Application): AndroidViewModel(application) {
    val hasInit: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
}