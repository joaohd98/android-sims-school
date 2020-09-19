package screens.logged.tips.modal_medias

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasBinding
import repositories.tips.TipsResponse
import screens.logged.tips.modal_medias.adapter.MediasAdapter
import utils.CubeTransformer
import utils.shortLongPress

class MediasModal(
    tips: ArrayList<TipsResponse>,
    private val initialIndex: Int,
) : DialogFragment() {
    private val viewModel: MediasViewModel by activityViewModels {
        MediasViewModelFactory(tips, initialIndex)
    }
    lateinit var binding: ModalMediasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.modal_medias, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.actualTipPosition.value = initialIndex

        setFunViewModel()
        initViewPager()
        initTouchListener()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireActivity(), R.style.FullScreenModal) {
            override fun onBackPressed() {
                onLeavePage()
            }
        }
    }

    override fun onPause() {
        super.onPause()

        (binding.modalMediasViewPager.adapter as MediasAdapter).apply {
            onEnterBackground()
        }
    }

    private fun setFunViewModel() {
        val closeModal = fun() {
            dismiss()
        }

        val reachLastPositionMedia = fun(position: Int) {
            binding.modalMediasViewPager.apply {
                val viewChild = getChildAt(0)

                isUserInputEnabled = false
                viewChild.isEnabled = false
                setCurrentItem(position, true)

                Handler().postDelayed({
                    isUserInputEnabled = true
                    viewChild.isEnabled = true
                }, 500)
            }
        }

        viewModel.setFun({
            viewModel.getCurrentTip().apply {
                val size = viewModel.tips.size

                val changeCurrent = fun(compareValue: Int, sumValue: Int) {
                    val position = viewModel.getActualTipPosition()

                    if (position == compareValue) {
                        closeModal()
                    } else {
                        val newPosition = position + sumValue
                        reachLastPositionMedia(newPosition)
                        viewModel.actualTipPosition.value = newPosition
                    }
                }

                if (it) {
                    goRightMedia {
                        changeCurrent(size - 1, 1)
                    }
                } else {
                    goLeftMedia {
                        changeCurrent(0, -1)
                    }
                }

                viewModel.changeTappedDirection()
            }
        }, { position ->
            reachLastPositionMedia(position)
        }) {
            closeModal()
        }
    }

    private fun initViewPager(){
        val size = viewModel.tips.size

        val fragmentActivity = context as FragmentActivity
        val adapter =  MediasAdapter(fragmentActivity, initialIndex, size) {
            onLeavePage()
        }

        binding.modalMediasViewPager.apply {
            this.adapter = adapter
            offscreenPageLimit = size
            setPageTransformer(CubeTransformer())
            setCurrentItem(initialIndex, false)
            setObserves()
        }
    }

    private fun initTouchListener() {
        binding.modalMediasViewPager.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        ViewPager2.SCROLL_STATE_DRAGGING -> viewModel.changeSliding(true)
                        ViewPager2.SCROLL_STATE_SETTLING -> viewModel.changeSliding(false)
                        else -> {
                        }
                    }
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewModel.actualTipPosition.value = position
                }
            })

            val viewChild = getChildAt(0)
            viewChild.shortLongPress({}, {
                viewModel.changeHolding(false)
            }, {
                if (!(viewModel.isSliding.value!!)) {
                    viewModel.changeHolding(true)
                    binding.modalMediasViewPager.isUserInputEnabled = false
                }
            }, { event ->
                val x = event.x.toInt()
                val middle = Resources.getSystem().displayMetrics.widthPixels / 2

                viewModel.positionChanged(x > middle)
            })
        }
    }

    private fun setObserves() {
        val adapter = binding.modalMediasViewPager.adapter as MediasAdapter

        viewModel.isHolding.observe(viewLifecycleOwner, {
            adapter.changeHolding(it)

            if(!it) {
                binding.modalMediasViewPager.isUserInputEnabled = true
            }
        })

        viewModel.isSliding.observe(viewLifecycleOwner, {
            adapter.changeSliding(it)
        })

        viewModel.hasTappedDirection.observe(viewLifecycleOwner, {
            adapter.changedMedia()
        })

        viewModel.actualTipPosition.observe(viewLifecycleOwner, {
            adapter.changeActualTipPosition(it)
        })
    }

    private fun onEnterBackground(isEntering: Boolean) {

    }

    private fun onLeavePage() {
        viewModel.onLeavePage()

        (binding.modalMediasViewPager.adapter as MediasAdapter).apply {
            onLeavePage()
            dismiss()
        }
    }

    companion object {
        fun invoke(
            fragmentManager: FragmentManager,
            tips: ArrayList<TipsResponse>,
            index: Int
        ): MediasModal {
            val mediaModal = MediasModal(tips, index)
            mediaModal.show(fragmentManager, mediaModal.tag)

            return mediaModal
        }
    }
}