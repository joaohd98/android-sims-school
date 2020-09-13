package screens.logged.tabs.tips.modal_medias.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemProgressViewBinding
import utils.getPixels


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
            LayoutInflater.from(context).inflate(
                R.layout.modal_medias_item_progress_view,
                this,
                true
            )
        }
        else {
            binding = ModalMediasItemProgressViewBinding.inflate(
                LayoutInflater.from(context),
                this,
                true
            )
        }
    }
    fun initProgressView(size: Int) {
        binding.modalMediasItemProgressLinearLayout.apply {
            weightSum = size.toFloat()

            for (i in 0 until size) {
                val view = getProgressBar()
                addView(view)
            }
        }
    }

    private fun getProgressBar(): View {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.modal_medias_item_progress_bar, null)

        view.apply {
            val dimen = context.getPixels(3)
            val params = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT, 1f)

            params.setMargins(dimen, marginTop, dimen, marginBottom)
            layoutParams = params
        }

        return view
    }
}