package screens.logged.tips

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ScreenTipsBinding
import components.error_view.OnTryAgainClickDataBinding
import kotlinx.android.synthetic.main.screen_scores.*
import repositories.RepositoryStatus
import screens.logged.scores.ScoresViewModel
import utils.observeOnce


class TipsScreen : Fragment() {
    private val viewModel: TipsViewModel by viewModels()
    lateinit var binding: ScreenTipsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.screen_tips, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callRequest()
        initRecyclerView()
        initErrorView()
        setObserves()
    }

    private fun callRequest() {
        viewModel.user.observeOnce(viewLifecycleOwner) {
            if (it != null) {
                viewModel.callTips()
            }
        }
    }

    private fun initRecyclerView() {
        binding.screenTipsList.initRecyclerView(activity?.supportFragmentManager!!)
    }

    private fun setObserves() {
        val list = binding.screenTipsList

        viewModel.statusTips.observe(viewLifecycleOwner, {
            when(it) {
                RepositoryStatus.FAILED -> {
                    binding.screenTipsViewSwitcher.showNext()
                }
                RepositoryStatus.LOADING -> {
                    list.setLoading()
                }
                RepositoryStatus.SUCCESS -> {
                    list.setSuccess(ArrayList(viewModel.tips))
                }
                else -> {}
            }
        })
    }

    private fun initErrorView() {
        binding.screenTipsErrorView.setTryAgain(object: OnTryAgainClickDataBinding {
            override fun showLoading() {
                binding.screenTipsViewSwitcher.showNext()
            }

            override fun onClick() {
                viewModel.callTips(true)
            }
        })
    }
}