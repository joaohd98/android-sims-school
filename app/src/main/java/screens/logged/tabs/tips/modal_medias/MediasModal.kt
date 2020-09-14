package screens.logged.tabs.tips.modal_medias

import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasBinding
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_medias.adapter.MediasAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenModal);
    }

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

        initViewPager()
        initTouchListener()
    }

    private fun initViewPager(){
        val actualTipPosition = viewModel.getActualTipPosition()

        val fragmentActivity = context as FragmentActivity
        val adapter =  MediasAdapter(fragmentActivity, actualTipPosition, viewModel.tips.size) {
            dismiss()
        }

        binding.modalMediasViewPager.apply {
            this.adapter = adapter
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
                        else -> { }
                    }
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewModel.actualTipPosition.value = position
                }
            })

            val viewChild = getChildAt(0)
            viewChild.shortLongPress( {}, {
                viewModel.changeHolding(false)
            }, {
                viewModel.changeHolding(true)
            }, { event ->
                val x = event.x.toInt()
                val middle = Resources.getSystem().displayMetrics.widthPixels / 2

                viewModel.positionChanged(x > middle,  { position ->
                    isUserInputEnabled = false
                    viewChild.isEnabled = false
                    setCurrentItem(position, true)

                    Handler().postDelayed({
                        isUserInputEnabled = true
                        viewChild.isEnabled = true
                    }, 500)
                }) {
                    dismiss()
                }
            })
        }
    }

    private fun setObserves() {
        val adapter = binding.modalMediasViewPager.adapter as MediasAdapter

        viewModel.isHolding.observe(viewLifecycleOwner, {
            adapter.changeHolding(it)
        })

        viewModel.hasTappedDirection.observe(viewLifecycleOwner, {
            adapter.changedMedia()
        })

        viewModel.actualTipPosition.observe(viewLifecycleOwner, {
            adapter.changeActualTipPosition(it)
        })
    }

    companion object {
        fun invoke(
            fragmentManager: FragmentManager,
            tips: ArrayList<TipsResponse>,
            index: Int
        ) {
            val mediaModal = MediasModal(tips, index)
            mediaModal.show(fragmentManager, mediaModal.tag)
        }
    }
}