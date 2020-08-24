package screens.logged.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.joao.simsschool.R
import com.joao.simsschool.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import screens.logged.home.components.HomeChangePictureFragment
import screens.logged.home.components.HomeProfileView

class HomeScreen : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding =  DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profile = home_screen_profile as HomeProfileView

        viewModel.user.observe(viewLifecycleOwner, {
            profile.setUser(it)

            if(it != null){
                val bottomSheetFragment = HomeChangePictureFragment()
                bottomSheetFragment.show(activity?.supportFragmentManager!!, bottomSheetFragment.tag)

            }
        })

    }



}