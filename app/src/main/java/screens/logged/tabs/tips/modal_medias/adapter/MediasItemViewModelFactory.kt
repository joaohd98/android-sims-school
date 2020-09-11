package screens.logged.tabs.tips.modal_medias.adapter

import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import repositories.tips.TipsMediasResponse


@Suppress("UNCHECKED_CAST")
class MediasItemViewModelFactory(
    private val medias: ArrayList<TipsMediasResponse>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MediasItemViewModel(medias) as T
    }
}