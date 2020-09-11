package screens.logged.tabs.tips.modal_medias

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MediasViewModel: ViewModel() {
    val isHolding: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val isSliding: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun changeHolding(isHolding: Boolean) {
        this.isHolding.value = isHolding
    }

    fun changeSliding(isSliding: Boolean) {
        this.isSliding.value = isSliding
    }
}