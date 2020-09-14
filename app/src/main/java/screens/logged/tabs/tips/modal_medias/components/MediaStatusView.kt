package screens.logged.tabs.tips.modal_medias.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemStatusViewBinding

class MediaStatusView: ConstraintLayout {
    lateinit var binding: ModalMediasItemStatusViewBinding

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
}