package screens.logged.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_home_profile.*
import repositories.RepositoryStatus
import screens.logged.home.components.HomeClassesViewAdapter
import screens.logged.home.components.HomeProfileView
import utils.alertDialog
import utils.observeOnce

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

        callRequests()
        setProfile()
        setClasses()
    }

    private fun callRequests() {
        viewModel.user.observeOnce(viewLifecycleOwner) {
            if (it != null) {
                viewModel.callClasses()
            }
        }
    }

    private fun setProfile() {
        val profile = home_screen_profile as HomeProfileView
        profile.setChangeProfile(viewModel, activity?.supportFragmentManager!!)

        viewModel.user.observe(viewLifecycleOwner, {
            profile.setUser(it)
        })

        viewModel.statusChangeProfile.observe(viewLifecycleOwner, { status ->
            val uriImageView = view_home_profile_picture
            when(status) {
                RepositoryStatus.FAILED -> {
                    activity?.alertDialog(
                        "Something happened while changing profile picture\n\nTry again later!"
                    )
                }
                RepositoryStatus.LOADING -> {
                    uriImageView.setLoadScreen()
                }
                RepositoryStatus.SUCCESS -> {
                    uriImageView.setSuccessScreen(viewModel.user.value?.profile_picture!!)
                }
                else -> {}
            }
        })
    }

    private fun setClasses() {
        val classesContainer = home_screen_classes_container
        classesContainer.setClasses(requireContext())

        viewModel.statusClass.observe(viewLifecycleOwner, { status ->
            when(status) {
                RepositoryStatus.FAILED -> {
                    classesContainer.setFailed()

                }
                RepositoryStatus.LOADING -> {
                    classesContainer.setLoading()

                }
                RepositoryStatus.SUCCESS -> {
                    classesContainer.setSuccess()
                }
                else -> {}
            }
        })

    }
}