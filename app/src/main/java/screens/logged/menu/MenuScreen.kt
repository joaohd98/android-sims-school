package screens.logged.menu

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ScreenMenuBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import utils.OnClickDataBinding

class MenuScreen : Fragment() {
    private val viewModel: MenuViewModel by viewModels()
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

        setClickShare()
        setClickMaps()
        setClickLogout()
    }

    private fun setClickShare() {
        binding.screenMenuOptionShare.setOnCLick(object: OnClickDataBinding() {
            override fun onClick() {
                viewModel.share(context as Activity)
            }
        })
    }

    private fun setClickMaps() {
        binding.screenMenuOptionMaps.setOnCLick(object: OnClickDataBinding() {
            override fun onClick() {
                findNavController().navigate(R.id.action_menuScreen_to_mapsScreen)
            }
        })
    }

    private fun setClickLogout() {
        binding.screenMenuLogout.setOnCLick(object: OnClickDataBinding() {
            override fun onClick() {
                val navController = findNavController()

                GlobalScope.launch(Dispatchers.IO) {
                    viewModel.logout()

                    GlobalScope.launch(Dispatchers.Main) {
                        navController.navigate(R.id.action_menuScreen_to_loginScreen)
                    }
                }
            }
        })
    }

}