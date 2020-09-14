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

    val hasTappedDirection: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
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

    fun positionChanged(isRight: Boolean, onChange: (Int) -> Unit, onDismiss: () -> Unit) {
        getCurrentTip().apply {
            val size = tips.size

            val changeCurrent = fun(compareValue: Int, sumValue: Int) {
                val position = getActualTipPosition()

                if(position == compareValue) {
                    onDismiss()
                }
                else {
                    val newPosition = position + sumValue
                    onChange(newPosition)
                    actualTipPosition.value = newPosition
                }
            }

            if(isRight) {
                goRightMedia {
                   changeCurrent(size - 1, 1)
                }
            }
            else {
                goLeftMedia {
                    changeCurrent(0, -1)
                }
            }

            hasTappedDirection.value = !(hasTappedDirection.value!!)
        }
    }
}