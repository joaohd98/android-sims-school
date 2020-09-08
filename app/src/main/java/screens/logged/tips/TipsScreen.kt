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
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ScreenTipsBinding
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
        setObserves()
        binding.screenTipsImage.clipToOutline = true
    }

    private fun callRequest() {
        viewModel.user.observeOnce(viewLifecycleOwner) {
            if (it != null) {
                viewModel.callTips()
            }
        }
    }


    private fun setObserves() {
        viewModel.statusTips.observe(viewLifecycleOwner, {
            when(it) {
                RepositoryStatus.FAILED -> {
                    Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show()
                }
                RepositoryStatus.LOADING -> {
                    Toast.makeText(context, "LOADING", Toast.LENGTH_SHORT).show()

                }
                RepositoryStatus.SUCCESS -> {
                    Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        })
    }
}