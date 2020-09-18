package screens.logged.tips.modal_medias

import android.util.Log
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

    val hasTappedDirection: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    private lateinit var onChangeCurrentMedia: (Boolean) -> Unit
    private lateinit var reachLastPositionMedia: (Int) -> Unit
    private lateinit var closeModal: () -> Unit

    fun setFun(onChangeCurrentMedia: (Boolean) -> Unit, onLastPositionMedia: (Int) -> Unit, closeModal: () -> Unit) {
        this.onChangeCurrentMedia = onChangeCurrentMedia
        this.reachLastPositionMedia = onLastPositionMedia
        this.closeModal = closeModal
    }

    fun getActualTipPosition() = actualTipPosition.value!!

    fun getActualTip(position: Int) = tips[position]

    fun getCurrentTip() = tips[getActualTipPosition()]

    fun changeHolding(isHolding: Boolean) {
        this.isHolding.value = isHolding
    }

    fun changeSliding(isSliding: Boolean) {
        this.isSliding.value = isSliding
    }

    fun changeTappedDirection() {
        hasTappedDirection.value = !(hasTappedDirection.value!!)
    }

    fun positionChanged(isRight: Boolean) {
      onChangeCurrentMedia(isRight)
    }

    fun onLeavePage() {
        tips.forEach {
            it.reset()
        }
    }
}