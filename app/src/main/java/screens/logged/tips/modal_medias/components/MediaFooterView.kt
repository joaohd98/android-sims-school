package screens.logged.tips.modal_medias.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.WebView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemFooterViewBinding
import components.web_view.WebViewFragment
import repositories.tips.TipsMediasResponse
import utils.OnClickDataBinding


class MediaFooterView: ConstraintLayout {
    lateinit var binding: ModalMediasItemFooterViewBinding
    lateinit var fragmentManager: FragmentManager
    lateinit var url: Uri

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.modal_medias_item_footer_view, this, true)
        }
        else {
            binding = ModalMediasItemFooterViewBinding.inflate(
                LayoutInflater.from(context),
                this,
                true
            )
        }
    }

    fun initFooterView(media: TipsMediasResponse, fragmentManager: FragmentManager, onChange: (Boolean) -> Unit) {
        changeFooterLink(media)

        this.fragmentManager = fragmentManager
        binding.onButtonPress = setOnPress(onChange)
    }

    fun changeFooterLink(media: TipsMediasResponse) {
        url = Uri.parse(media.url)
    }

    private fun setOnPress(onChange: (Boolean) -> Unit): OnClickDataBinding {
        return object: OnClickDataBinding() {
            override fun onClick() {
                WebViewFragment.invoke(fragmentManager , url, onChange)
            }
        }
    }

}