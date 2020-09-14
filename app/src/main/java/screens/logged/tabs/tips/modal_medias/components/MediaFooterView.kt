package screens.logged.tabs.tips.modal_medias.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemFooterViewBinding
import repositories.tips.TipsMediasResponse
import utils.OnClickDataBinding


class MediaFooterView: ConstraintLayout {
    lateinit var binding: ModalMediasItemFooterViewBinding

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

    fun initFooterView(media: TipsMediasResponse) {
        binding.onButtonPress = setOnPress(media.url)
    }

    fun changeFooterLink(url: String) {
        binding.onButtonPress = setOnPress(url)
    }

    private fun setOnPress(url: String): OnClickDataBinding {
        return object: OnClickDataBinding() {
            override fun onClick() {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(context, browserIntent, null)
            }
        }
    }

}