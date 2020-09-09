package components.uri_image

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.components_progress_bar_circular.view.*
import kotlinx.android.synthetic.main.components_view_uri_image.view.*

class UriImageView : LinearLayout {
    var uri: String? = null

    constructor(context: Context) : super(context) {
        init(null, 0)
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.components_view_uri_image, this, true)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.UriImageView, defStyle, 0
        )

        val background = typedArray.getDrawable(
            R.styleable.UriImageView_background_image
        )

        if(background != null) {
            uri_image_shimmer.background = background
            uri_image.background = background
            uri_image.clipToOutline = true
        }

        val scaleType = typedArray.getInt(
            R.styleable.UriImageView_scale_type_image,
            -1
        )

        if(scaleType > -1) {
            uri_image.scaleType = getScaleType(scaleType)
        }

        typedArray.recycle()
    }

    private fun getScaleType(value: Int): ScaleType {
        return when(value) {
            0 -> ScaleType.MATRIX
            1 -> ScaleType.FIT_XY
            2 -> ScaleType.FIT_START
            3 -> ScaleType.FIT_CENTER
            4 -> ScaleType.FIT_END
            5 -> ScaleType.CENTER
            6 -> ScaleType.CENTER_CROP
            7 -> ScaleType.CENTER_INSIDE
            else -> { ScaleType.FIT_XY }
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        uri_image_try_again.setOnClickListener {
            uri_image_try_again.visibility = INVISIBLE

            shimmerChange(true)

            Handler().postDelayed({
                startLoadingImg()
            }, 2000)
        }
    }

    fun setLoadScreen() {
        uri_image.setImageDrawable(null)
        shimmerChange(true)
    }

    fun setSuccessScreen(uri: String) {
        this.uri = uri
        startLoadingImg()
    }

    fun startLoadingImg() {
        shimmerChange(true)

        Glide
            .with(uri_image.context)
            .load(Uri.parse(uri))
            .timeout(5000)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    finishLoad(false)
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    finishLoad(true)
                    return false
                }

            })
            .placeholder(R.drawable.skeleton)
            .into(uri_image)
    }

    private fun finishLoad(isSuccess: Boolean) {
        shimmerChange(false)

        if (!isSuccess) {
            uri_image_try_again.visibility = VISIBLE
        }
        else {
            isEnabled = true
            isClickable = true
        }
    }

    private fun shimmerChange(isToStart: Boolean) {
        val shimmer = uri_image_shimmer as ShimmerFrameLayout
        if(isToStart) {
            isEnabled = false
            isClickable = false
            shimmer.showShimmer(true)
        }
        else {
            shimmer.hideShimmer()
        }
    }

}