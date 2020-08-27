package screens.logged.home.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewHomeAdsBinding
import components.error_view.OnTryAgainClickDataBinding
import kotlinx.android.synthetic.main.view_home_ads.view.*
import kotlinx.android.synthetic.main.view_home_classes_container.view.*
import repositories.ads.AdsResponse

class HomeAdsView : ConstraintLayout {
    lateinit var binding: ViewHomeAdsBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_home_ads, this, true)
        }
        else {
            binding = ViewHomeAdsBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        view_home_ads_image.clipToOutline = true
    }

    fun setSuccess(ad: AdsResponse) {
        binding.ad = ad
    }

    fun setLoading() {
        view_home_ads_image.setLoadScreen()
    }

    fun setFailed() {
        view_home_ads_switcher.showNext()
    }

    fun setTryAgain(onTryAgain: () -> Unit) {
        binding.tryAgain = object: OnTryAgainClickDataBinding {
            override fun showLoading() {
                view_home_ads_switcher.showNext()
            }
            override fun onClick() {
                onTryAgain()
            }
        }
    }
}
