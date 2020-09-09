package screens.logged.tabs.home.components

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewHomeAdsBinding
import components.error_view.OnTryAgainClickDataBinding
import components.web_view.WebViewFragment
import kotlinx.android.synthetic.main.view_home_ads.view.*
import repositories.ads.AdsResponse
import utils.OnClickDataBinding


class HomeAdsView : ConstraintLayout {
    lateinit var binding: ViewHomeAdsBinding
    lateinit var ad: AdsResponse

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

    fun setWebView(supportFragmentManager: FragmentManager) {
        binding.webView = object: OnClickDataBinding {
            override fun onClick() {
                WebViewFragment.invoke(supportFragmentManager, Uri.parse(ad.url))
            }
        }
    }

    fun setSuccess(ad: AdsResponse) {
        this.ad = ad
        binding.ad = ad
    }

    fun setLoading() {
        view_home_ads_image.showLoadingScreen()
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
