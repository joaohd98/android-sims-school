package screens.logged.tips.modal_medias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import repositories.tips.TipsResponse

class MediasViewModelFactory(
    private val tips: ArrayList<TipsResponse>,
    private val initialIndex: Int
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ArrayList::class.java, Int::class.java).newInstance(tips, initialIndex)
    }
}