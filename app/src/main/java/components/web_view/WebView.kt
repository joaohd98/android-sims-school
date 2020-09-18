package components.web_view

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentManager
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.components_fragment_web_view.*
import utils.CustomRoundBottomSheet

class WebViewFragment(private val url: Uri, private val onChange: (Boolean) -> Unit) : CustomRoundBottomSheet() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.components_fragment_web_view, container, false)
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetRoundedTheme
    }

    override fun onStart() {
        super.onStart()
        set90Screen()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()
        setBackButton()
        setWebView()
    }

    private fun setTitle() {
        fragment_web_view_title.text = url.host?.replace("www.", "")
    }

    private fun setBackButton() {
        fragment_web_view_close.setOnClickListener {
            dismiss()
            fragment_web_view.destroy()
        }
    }

    private fun setWebView() {
        val webView = fragment_web_view

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                webView.visibility = View.VISIBLE

                fragment_web_view_progress_bar?.apply {
                    visibility = View.GONE
                }
            }
        }

        webView.settings.javaScriptEnabled = true
        webView.settings.allowContentAccess = true

        webView.loadUrl(url.toString())
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onChange(false)
    }

    companion object {
        fun invoke(fragmentManager: FragmentManager, uri: Uri, onChange: (Boolean) -> Unit = {}) {
            val bottomSheetFragment = WebViewFragment(uri, onChange)
            bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)

            onChange(true)
        }
    }
}

