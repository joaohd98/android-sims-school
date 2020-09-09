package screens.logged.menu

import activities.logged.LoggedActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ScreenMenuBinding
import com.joao.simsschool.databinding.ScreenTipsBinding

class MenuScreen : Fragment() {
    lateinit var binding: ScreenMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.screen_menu, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}