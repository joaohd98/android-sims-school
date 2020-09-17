package screens.logged.tips.modal_medias.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemStatusViewBinding
import components.error_view.OnTryAgainClickDataBinding
import repositories.RepositoryStatus
import repositories.tips.TipsMediasResponse

class MediaStatusView: ConstraintLayout {
    private lateinit var binding: ModalMediasItemStatusViewBinding
    private lateinit var callService: () -> Unit

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.modal_medias_item_status_view, this, true)
        }
        else {
            binding = ModalMediasItemStatusViewBinding.inflate(
                LayoutInflater.from(context),
                this,
                true
            )
        }
    }

    fun initStatusView() {
        binding.modalMediasItemStatusErrorView.setTryAgain(object: OnTryAgainClickDataBinding {
            override fun showLoading() {
                changeStatus(RepositoryStatus.LOADING)
            }

            override fun onClick() {
                callService()
            }
        })
    }

    fun setCallRequest(media: TipsMediasResponse, stillCurrent: () -> Boolean, onSuccess: () -> Unit) {
        changeStatus(media.status)

        if(media.status == RepositoryStatus.FAILED) {
            return
        }
        if(media.status == RepositoryStatus.SUCCESS) {
            onSuccess()
            return
        }

        callService = {
            media.callService(context, {
                if(stillCurrent()) {

                    Log.d("aaa", "receive ${media.url}")

                    changeStatus(RepositoryStatus.SUCCESS)
                    onSuccess()
                }
            }, {
                if(stillCurrent()) {
                    changeStatus(RepositoryStatus.FAILED)
                }
            })
        }

        callService()
    }

    fun changeStatus(status: RepositoryStatus) {
        when(status) {
            RepositoryStatus.LOADING -> {
                binding.modalMediasItemStatusLinearLayout.alpha = 1f
                binding.modalMediasItemStatusViewSwitcher.displayedChild = 0
            }
            RepositoryStatus.FAILED -> {
                binding.modalMediasItemStatusLinearLayout.alpha = 1f
                binding.modalMediasItemStatusViewSwitcher.displayedChild = 1
            }
            RepositoryStatus.SUCCESS -> {
                binding.modalMediasItemStatusLinearLayout.alpha = 0f
            }
            else -> {}
        }
    }
}