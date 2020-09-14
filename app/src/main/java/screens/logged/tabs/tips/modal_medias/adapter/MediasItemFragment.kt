package screens.logged.tabs.tips.modal_medias.adapter

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemBinding
import screens.logged.tabs.tips.modal_medias.MediasViewModel

class MediasItemFragment(
    private val position: Int,
    private val onDismiss: () -> Unit
): Fragment() {
    private lateinit var binding: ModalMediasItemBinding
    private val viewModel: MediasViewModel by activityViewModels()
    private var limitTimer = 0
    private var timerCount = 0
    private var delay = 10
    private var started = false
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.modal_medias_item, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProgressView()
        setTitleView()
        setFooterView()
    }

    private fun hasInitialized() = this::binding.isInitialized

    fun isActiveTip() {
        startImageTimer()
    }

    fun isLastActiveTip() {
        stopTimer()
        binding.modalMediasItemProgressView.changeProgress(0.0)
    }

    fun changeHolding(isHolding: Boolean) {
        binding.isHolding = isHolding
    }

    fun changeSliding(isSliding: Boolean) {
        if(hasInitialized()) {
            binding.isSliding = isSliding
        }
    }

    fun changedMedia() {
        val tip = viewModel.getActualTip(position)

        binding.modalMediasItemProgressView.newCurrentPosition(tip.currentMediaPosition)
        binding.modalMediasItemFooterView.changeFooterLink(tip.getMedia().url)

        startImageTimer()
    }

    fun onLeavePage() {
        viewModel.getActualTip(position).reset()
        stopTimer()
    }

    fun onEnterBackground(isEntering: Boolean) {
        if(isEntering) {
            stopTimer()
        }
        else {
            startTimer()
        }
    }

    private val runnableTimer = Runnable {
        if(!(viewModel.isHolding.value!! || viewModel.isSliding.value!!)) {
            timerCount -= delay

            if(timerCount > 0) {
                val value = 100 - (timerCount.toDouble() / limitTimer.toDouble() * 100)
                binding.modalMediasItemProgressView.changeProgress(value)
            }
            else {
                stopTimer()

                binding.modalMediasItemProgressView.changeProgress(100.0)
                viewModel.positionChanged(true)
            }
        }

        if (started) {
            startTimer()
        }
    }

    private fun startImageTimer() {
        stopTimer()

        val time = 6000
        limitTimer = time
        timerCount = time

        startTimer()
    }

    private fun stopTimer() {
        started = false
        handler.removeCallbacks(runnableTimer)
    }

    private fun startTimer() {
        started = true
        handler.postDelayed(runnableTimer, delay.toLong())
    }

    private fun setProgressView() {
        val tip = viewModel.getActualTip(position)

        binding.modalMediasItemProgressView.apply {
            initProgressView(tip.getMediaSize())
        }
    }

    private fun setTitleView() {
        val tip = viewModel.getActualTip(position)

        binding.modalMediasItemTitleView.apply {
            initTitleView(tip.name, onDismiss)
        }
    }

    private fun setFooterView() {
        val media = viewModel.getActualTip(position).getMedia()

        binding.modalMediasItemFooterView.apply {
            initFooterView(media)
        }
    }
}