package components.uri_image

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.view_uri_image.view.*

class UriImageView : ConstraintLayout {
    var uri: String? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.view_uri_image, this, true)
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