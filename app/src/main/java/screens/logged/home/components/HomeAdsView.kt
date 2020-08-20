package screens.logged.home.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewHomeAdsBinding

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
            LayoutInflater.from(context).inflate(R.layout.view_home_profile, this, true)
        }
        else {
            binding = ViewHomeAdsBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }
}
