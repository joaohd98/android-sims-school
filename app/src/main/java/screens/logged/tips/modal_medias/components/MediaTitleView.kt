package screens.logged.tips.modal_medias.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemTitleViewBinding
import utils.OnClickDataBinding

class MediaTitleView: ConstraintLayout {
    lateinit var binding: ModalMediasItemTitleViewBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.modal_medias_item_title_view, this, true)
        }
        else {
            binding = ModalMediasItemTitleViewBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun initTitleView(name: String, onDismiss: () -> Unit) {
        binding.modalMediasItemTitleViewText.text = name
        binding.backButton = object: OnClickDataBinding() {
            override fun onClick() {
                onDismiss()
            }
        }
    }

}