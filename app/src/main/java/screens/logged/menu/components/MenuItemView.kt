package screens.logged.menu.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewMenuItemBinding
import utils.OnClickDataBinding

class MenuItemView: LinearLayout {
    lateinit var binding: ViewMenuItemBinding

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

        typedArray.recycle()
    }

    fun setOnCLick(call: OnClickDataBinding) {
        binding.onButton = call
    }
}