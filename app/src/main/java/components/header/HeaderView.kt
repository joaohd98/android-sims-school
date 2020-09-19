package components.header

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ComponentsViewErrorBinding
import com.joao.simsschool.databinding.ComponentsViewHeaderBinding
import kotlinx.android.synthetic.main.components_view_error.view.*
import utils.OnClickDataBinding

class HeaderView: ConstraintLayout {
    private lateinit var binding: ComponentsViewHeaderBinding
    private var navController: NavController? = null

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
            LayoutInflater.from(context).inflate(R.layout.components_view_header, this, true)
        }
        else {
            binding = ComponentsViewHeaderBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.HeaderView, defStyle, 0
        )

        val title = typedArray.getString(R.styleable.HeaderView_title)
        if(title != null) {
            binding.componentsViewHeaderTitle.text = title
        }

        binding.componentsViewHeaderButton.visibility = booleanToVisibility(typedArray.getBoolean(
            R.styleable.HeaderView_hasButton, true
        ))

        binding.componentsViewHeaderTabs.visibility = booleanToVisibility(typedArray.getBoolean(
            R.styleable.HeaderView_hasTabs, true
        ))


        binding.componentsViewHeaderLeftArrow.visibility = booleanToVisibility(typedArray.getBoolean(
            R.styleable.HeaderView_hasBackButton, false
        ))

        typedArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        val activity = (context as Activity)

        binding.onBackClick = object: OnClickDataBinding() {
            override fun onClick() {
                activity.onBackPressed()
            }
        }

        binding.onMenuClick = object: OnClickDataBinding() {
            override fun onClick() {
                navController?.navigate(R.id.action_tabsFragment_to_menuScreen)
            }
        }

    }


    private fun booleanToVisibility(value: Boolean): Int {
        return if(value) View.VISIBLE else View.GONE
    }

    fun getTabLayout(): TabLayout {
        return binding.componentsViewHeaderTabs
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }
}