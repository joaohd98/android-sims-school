package screens.logged.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ScreenHomeBinding
import kotlinx.android.synthetic.main.screen_home.*
import kotlinx.android.synthetic.main.view_home_profile.*
import repositories.RepositoryStatus
import utils.FragmentFromTab
import utils.alertDialog
import utils.observeOnce

class HomeScreen : FragmentFromTab() {
    lateinit var binding: ScreenHomeBinding
    override val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.screen_home, container, false
        )

        return binding.root
    }

    override fun initMethod() {
        callRequests()
        setProfile()
        setClasses()
        setAds()
        setObserves()
    }
    
    private fun callRequests() {
        viewModel.user.observeOnce(viewLifecycleOwner) {
            if (it != null) {
                viewModel.callClasses()
                viewModel.callAds()
            }
        }
    }

    private fun setProfile() {
        binding.homeScreenProfile.setChangeProfile(viewModel, activity?.supportFragmentManager!!)
    }

    private fun setClasses() {
        binding.homeScreenClassesContainer.setClasses(context as FragmentActivity) {
            viewModel.callClasses(true)
        }

    }

    private fun setAds() {
        val adsView = home_screen_ads
        binding.homeScreenAds.setWebView(activity?.supportFragmentManager!!) {
            viewModel.callAds(true)
        }
    }


    private fun setObserves() {
        viewModel.user.observe(viewLifecycleOwner, {
            binding.homeScreenProfile.setUser(it)
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
                    uriImageView.showLoadingScreen()
                }
                RepositoryStatus.SUCCESS -> {
                    uriImageView.startLoadingImage(viewModel.user.value?.profile_picture!!)
                }
                else -> {}
            }
        })

        viewModel.statusClass.observe(viewLifecycleOwner, { status ->
            val classesContainer = binding.homeScreenClassesContainer

            when(status) {
                RepositoryStatus.FAILED -> {
                    classesContainer.setFailed()
                }
                RepositoryStatus.LOADING -> {
                    classesContainer.setLoading()
                }
                RepositoryStatus.SUCCESS -> {
                    classesContainer.setSuccess(ArrayList(viewModel.classes), viewModel.actualClassIndex.value!!)
                }
                else -> {}
            }
        })

        viewModel.statusAds.observe(viewLifecycleOwner, { status ->
            val adsView = binding.homeScreenAds

            when(status) {
                RepositoryStatus.FAILED -> {
                    adsView.setFailed()
                }
                RepositoryStatus.LOADING -> {
                    adsView.setLoading()
                }
                RepositoryStatus.SUCCESS -> {
                    adsView.setSuccess(viewModel.ad.value!!)
                }
                else -> {}
            }
        })
    }

    companion object {
        fun newInstance() = HomeScreen()
    }
}

