package components.uri_image

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
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
import com.joao.simsschool.databinding.ViewInputBinding
import com.joao.simsschool.databinding.ViewUriImageBinding
import kotlinx.android.synthetic.main.view_uri_image.view.*

class UriImageView : ConstraintLayout {
    var uri: String? = null
    lateinit var binding: ViewUriImageBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_uri_image, this, true)
        }
        else {
            binding = ViewUriImageBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun startLoading() {
        shimmerChange(true)

        Glide
            .with(uri_image.context)
            .load(Uri.parse(uri))
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
    }

    private fun shimmerChange(isToStart: Boolean) {
        val shimmer = uri_image_shimmer as ShimmerFrameLayout
        if(isToStart) {
            shimmer.showShimmer(true)
        }
        else {
            shimmer.hideShimmer()
        }
    }

    //    private fun init(attrs: AttributeSet?, defStyle: Int) {
//        val styledAttributes = context.obtainStyledAttributes(
//            attrs, R.styleable.UriImageView, defStyle, 0
//        )
//
//        uri = styledAttributes.getString(
//            R.styleable.UriImageView_uri
//        )
//
//        styledAttributes.recycle()
//    }
}