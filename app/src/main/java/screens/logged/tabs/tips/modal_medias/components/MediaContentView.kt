package screens.logged.tabs.tips.modal_medias.components

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemContentBinding


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
            binding = ModalMediasItemContentBinding.inflate(
                LayoutInflater.from(context),
                this,
                true
            )
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

//        binding.modalMediaItemVideoVertical.apply {
//            val path = "android.resource://" + context.packageName.toString() + "/" + R.raw.terminator
//
//            setVideoURI(Uri.parse(path))
//            start()
//        }
    }
}