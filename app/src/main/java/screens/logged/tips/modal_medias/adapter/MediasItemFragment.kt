package screens.logged.tips.modal_medias.adapter

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
import screens.logged.tips.modal_medias.MediasViewModel
import java.lang.Runnable

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
        setStatusView()
        setFooterView()
        setFooterView()
    }

    fun isActiveTip() {
        makeCallMedia()

    }

    fun isLastActiveTip() {
        stopTimer()
        binding.modalMediasItemProgressView.changeProgress(0.0)
    }

    fun changeHolding(isHolding: Boolean) {
        binding.isHolding = isHolding
        binding.modalMediasItemContentView.isSlidingOrHolding(isHolding)
    }

    fun changeSliding(isSliding: Boolean) {
        binding.isSliding = isSliding
        binding.modalMediasItemContentView.isSlidingOrHolding(isSliding)
    }

    fun changedMedia() {
        stopTimer()

        val tip = viewModel.getActualTip(position)
        binding.modalMediasItemProgressView.newCurrentPosition(tip.currentMediaPosition)
        binding.modalMediasItemFooterView.changeFooterLink(tip.getMedia().url)

        makeCallMedia()
    }

    fun onLeavePage() {
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

    private fun makeCallMedia() {
        val media = viewModel.getActualTip(position).getMedia()
        Log.d("aaa", "call media ${media.url}")

        binding.modalMediasItemContentView.apply {
            eraseContent()

            binding.modalMediasItemStatusView.setCallRequest(media, {
                val isActualMedia = viewModel.getActualTip(position).getMedia().id == media.id
                val isActualTip = position == viewModel.getActualTipPosition()

                Log.d("aaa", "call media ${viewModel.getActualTip(position).getMedia().url}")

                isActualMedia && isActualTip
            }) {
                if(media.image != "") {
                    setImageTimer()
                }
                else {
                    setVideoTimer()
                }

                setContent(media)
            }
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

    private fun setImageTimer() {
        stopTimer()

        val time = 6000
        limitTimer = time
        timerCount = time

        startTimer()
    }

    private fun setVideoTimer() {
        stopTimer()

        val duration = viewModel.getActualTip(position).getMedia().videoDuration!!
        limitTimer = duration
        timerCount = duration

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

    private fun setStatusView() {
        binding.modalMediasItemStatusView.apply {
            initStatusView()
        }
    }

    private fun setFooterView() {
        val media = viewModel.getActualTip(position).getMedia()

        binding.modalMediasItemFooterView.apply {
            initFooterView(media)
        }
    }
}