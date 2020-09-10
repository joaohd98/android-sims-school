package screens.logged.tabs.tips.modal_medias.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemProgressViewBinding

class MediasProgressView: ConstraintLayout {
    lateinit var binding: ModalMediasItemProgressViewBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.modal_medias_item_progress_view, this, true)
        }
        else {
            binding = ModalMediasItemProgressViewBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
//        val value = 4f
//
//        binding.modalMediasItemProgressLinearLayout.weightSum = value
//        for (i in 0 until 4) {
//            binding.modalMediasItemProgressLinearLayout.addView(
//                binding.modalMediasItemProgressBar.rootView
//            )
//        }
    }

}