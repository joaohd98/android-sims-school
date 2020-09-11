package screens.logged.tabs.tips.modal_medias.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MediasItemViewModel: ViewModel() {
    var isSliding = true
    var isHolding = true

    val index: MutableLiveData<Int> by lazy {
        MutableLiveData(0)
    }

}