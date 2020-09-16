package screens.logged.tabs.tips.modal_medias.components

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
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
        if(media.imageBitmap != null) {
            setImage(media.imageBitmap!!, media.isVertical)
        }
        else {
            setVideo(media, media.isVertical)
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

    private fun setVideo(media: TipsMediasResponse, isVertical: Boolean) {
        binding.modalMediasItemContentSwitcherImageVideo.displayedChild = 1

        if(isVertical) {
            binding.modalMediasItemContentSwitcherVideo.displayedChild = 0
            initVideo(binding.modalMediasItemContentVideoVertical, media)
        }
        else {
            binding.modalMediasItemContentSwitcherVideo.displayedChild = 1
            initVideo(binding.modalMediasItemContentVideoHorizontal, media)
        }

    }

    private fun initVideo(videoView: VideoView, media: TipsMediasResponse) {
        videoView.apply {
            setVideoURI(media.videoAbsolutePath)
            start()
        }
    }

    fun isSlidingOrHolding(hasPause: Boolean) {

    }


    override fun onFinishInflate() {
        super.onFinishInflate()
    }
}