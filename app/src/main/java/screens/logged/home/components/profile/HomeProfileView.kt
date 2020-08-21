package screens.logged.home.components.profile

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewHomeProfileBinding
import repositories.user.UserResponse
import screens.logged.home.HomeViewModel

class HomeProfileView : ConstraintLayout {
    lateinit var binding: ViewHomeProfileBinding

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
            binding = ViewHomeProfileBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun setUser(user: UserResponse?) {
        binding.userResponse = user
    }

}