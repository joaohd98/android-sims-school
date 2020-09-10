package screens.logged.menu.components

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ShareCompat
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewMenuItemBinding

class MenuItemView: ConstraintLayout {
    lateinit var binding: ViewMenuItemBinding
    var typeButton: Int = -1

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
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_menu_item, this, true)
        }
        else {
            binding = ViewMenuItemBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        if (isInEditMode) {
            return
        }

        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.MenuItemView, defStyle, 0
        )

        binding.viewMenuItemImage.setImageDrawable(typedArray.getDrawable(
            R.styleable.MenuItemView_menu_icon
        ))

        binding.viewMenuItemImage.setColorFilter((typedArray.getColor(
            R.styleable.MenuItemView_menu_tint, context.getColor(R.color.gray)
        )))

        binding.viewMenuItemText.text = typedArray.getString(
            R.styleable.MenuItemView_menu_text
        )

        typeButton = typedArray.getInt(R.styleable.MenuItemView_menu_onPress, -1)

        typedArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding.viewMenuInnerLinearLayout.setOnClickListener {
            onCLick()
        }
    }

    private fun onCLick() {
        when(typeButton) {
            0 -> onShare()
            1 -> openMaps()
            2 -> logout()
            else -> {}
        }
    }

    private fun onShare()  {
        ShareCompat.IntentBuilder
            .from(context as Activity)
            .setText("""
                Hey,

			    Sims School is a simple Android APP, that I created with so much care

			    See the source code at: https://github.com/joaohd98/android-sims-school
            """.trimIndent())
            .setType("text/plain")
            .setChooserTitle("Sims School")
            .startChooser()
    }

    private fun openMaps() {

    }

    private fun logout() {

    }
}