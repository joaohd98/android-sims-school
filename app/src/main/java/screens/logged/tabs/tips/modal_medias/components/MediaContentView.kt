package screens.logged.tabs.tips.modal_medias.components

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemContentBinding
import repositories.tips.TipsMediasResponse


class MediaContentView: ConstraintLayout {
    private lateinit var binding: ModalMediasItemContentBinding

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

    fun setContent(media: TipsMediasResponse) {
        if(media.bitmapImage != null) {
            setImage(media.bitmapImage!!, media.isVertical)
        }
    }

    fun eraseContent() {
        binding.modalMediasItemContentImageVertical.setImageBitmap(null)
        binding.modalMediasItemContentImageHorizontal.setImageBitmap(null)
        binding.modalMediasItemContentVideoHorizontal.setVideoURI(null)
        binding.modalMediasItemContentVideoVertical.setVideoURI(null)
    }

    private fun setImage(bitmap: Bitmap, isVertical: Boolean) {
        binding.modalMediasItemContentSwitcherImageVideo.displayedChild = 0

        if(isVertical) {
            binding.modalMediasItemContentSwitcherImage.displayedChild = 0
            binding.modalMediasItemContentImageVertical.setImageBitmap(bitmap)
        }
        else {
            binding.modalMediasItemContentSwitcherImage.displayedChild = 1
            binding.modalMediasItemContentImageHorizontal.setImageBitmap(bitmap)
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