package screens.logged.tabs.tips.modal_medias.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemContentBinding
import com.joao.simsschool.databinding.ModalMediasItemProgressViewBinding
import com.joao.simsschool.databinding.ModalMediasItemTitleViewBinding

class MediaContentView: ConstraintLayout {
    lateinit var binding: ModalMediasItemContentBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.modal_medias_item_content, this, true)
        }
        else {
            binding = ModalMediasItemContentBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

}