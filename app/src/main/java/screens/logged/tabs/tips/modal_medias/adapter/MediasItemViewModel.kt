package screens.logged.tabs.tips.modal_medias.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import repositories.tips.TipsMediasResponse

class MediasItemViewModel(medias: ArrayList<TipsMediasResponse>): ViewModel() {
    val index: MutableLiveData<Int> by lazy {
        MutableLiveData(0)
    }

}