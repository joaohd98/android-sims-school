package screens.logged.tabs.tips.modal_medias

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import repositories.tips.TipsResponse

class MediasViewModel(response: ArrayList<TipsResponse>, index: Int): ViewModel() {
    val tips = mutableListOf<TipsResponse>().apply {
        addAll(response)
    }

    val actualTipPosition: MutableLiveData<Int> by lazy {
        MutableLiveData(index)
    }

    val isHolding: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val isSliding: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val changeList: MutableLiveData<Pair<Boolean, Int>> by lazy {
        MutableLiveData(Pair(false, -1))
    }

    fun getActualTipPosition() = actualTipPosition.value!!

    fun getActualTip(position: Int) = tips[position]

    fun changeHolding(isHolding: Boolean) {
        this.isHolding.value = isHolding
    }

    fun changeSliding(isSliding: Boolean) {
        this.isSliding.value = isSliding
    }

    fun positionChanged(position: Int, isRight: Boolean) {
        tips[position].apply {
            if(isRight) {
                goRightMedia {}
            }
            else {
                goLeftMedia {}
            }

            changeList.value = Pair(!(changeList.value!!.first), position)
        }
    }
}