package screens.logged.tips.modal_medias.components

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemContentBinding
import repositories.tips.TipsMediasResponse


class MediaContentView: ConstraintLayout {
    private lateinit var binding: ModalMediasItemContentBinding
    private var videoView: VideoView? = null
    private var hasPause: Boolean = false

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
        if(media.absolutePath != null) {
            if(media.image != "") {
                setImage(media.absolutePath!!, media.isVertical)
            }
            else {
                setVideo(media.absolutePath!!, media.isVertical)
            }
        }
    }

    fun eraseContent() {
        eraseImage(binding.modalMediasItemContentImageVertical)
        eraseImage(binding.modalMediasItemContentImageHorizontal)
        eraseVideo(binding.modalMediasItemContentVideoVertical)
        eraseVideo(binding.modalMediasItemContentVideoHorizontal)

        binding.modalMediasItemContentSwitcherImageVideo.visibility = INVISIBLE
        binding.modalMediasItemContentSwitcherVideo.visibility = INVISIBLE
        binding.modalMediasItemContentSwitcherImage.visibility = INVISIBLE

        binding.modalMediasItemContentSwitcherImageVideo.displayedChild = 0
        binding.modalMediasItemContentSwitcherImage.displayedChild = 0
    }

    private fun setImage(uri: Uri, isVertical: Boolean) {
        if(isVertical) {
            binding.modalMediasItemContentSwitcherImage.displayedChild = 0
            initImage(binding.modalMediasItemContentImageVertical, uri)
        }
        else {
            binding.modalMediasItemContentSwitcherImage.displayedChild = 1
            initImage(binding.modalMediasItemContentImageHorizontal, uri)
        }

        binding.modalMediasItemContentSwitcherImageVideo.displayedChild = 0
    }

    private fun setVideo(uri: Uri, isVertical: Boolean) {
        if(isVertical) {
            binding.modalMediasItemContentSwitcherVideo.displayedChild = 0
            initVideo(binding.modalMediasItemContentVideoVertical, uri)
        }
        else {
            binding.modalMediasItemContentSwitcherVideo.displayedChild = 1
            initVideo(binding.modalMediasItemContentVideoHorizontal, uri)
        }

        binding.modalMediasItemContentSwitcherImageVideo.displayedChild = 1

    }



    private fun eraseImage(imageView: ImageView) {
        imageView.apply {
            setImageBitmap(null)
        }
    }

    private fun eraseVideo(videoView: VideoView) {
        videoView.apply {
            stopPlayback()
            setVideoURI(null)
        }
    }

    private fun initImage(imageView: ImageView, uri: Uri) {
        imageView.apply {
            setImageURI(uri)
            binding.modalMediasItemContentSwitcherImage.visibility = VISIBLE
            binding.modalMediasItemContentSwitcherImageVideo.visibility = VISIBLE
        }
    }

    private fun initVideo(videoView: VideoView, uri: Uri) {
        this.videoView = videoView

        videoView.apply {
            setVideoURI(uri)

            if(hasPause) {
                setOnFirstFrameVideo(this)
            } else {
                start()
            }

            setOnPreparedListener {
                Handler().postDelayed({
                    binding.modalMediasItemContentSwitcherVideo.visibility = VISIBLE
                    binding.modalMediasItemContentSwitcherImageVideo.visibility = VISIBLE
                }, 100)
            }
        }
    }


    fun isSlidingOrHolding(hasPause: Boolean) {
        this.hasPause = hasPause

        if(this.videoView != null) {
            this.videoView?.apply {
                if(hasPause) {
                    pause()
                }
                else {
                    start()
                }
            }
        }
    }

    fun lastActiveTip() {
        setOnFirstFrameVideo(binding.modalMediasItemContentVideoVertical)
        setOnFirstFrameVideo(binding.modalMediasItemContentVideoHorizontal)
    }

    private fun setOnFirstFrameVideo(videoView: VideoView) {
        videoView.apply {
            seekTo(0)
            pause()
        }
    }

}