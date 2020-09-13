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


class MediasModal(initialIndex: Int, tips: ArrayList<TipsResponse>) : DialogFragment() {
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
        val adapter =  MediasAdapter(fragmentActivity, actualTipPosition, ArrayList(viewModel.tips))

        binding.modalMediasViewPager.apply {
            this.adapter = adapter

            setCurrentItem(actualTipPosition, false)
            setPageTransformer(CubeTransformer())

            Handler().postDelayed({
                setObserves()
            }, 50)
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

            getChildAt(0).shortLongPress( {}, {
                viewModel.changeHolding(false)
            }, {
                viewModel.changeHolding(true)
            }, { event ->
                val x = event.x.toInt()
                val middle = Resources.getSystem().displayMetrics.widthPixels / 2
                viewModel.positionChanged(viewModel.getActualTipPosition(),x > middle)
            })
        }
    }

    private fun setObserves() {
        val adapter = binding.modalMediasViewPager.adapter as MediasAdapter

        viewModel.isHolding.observe(viewLifecycleOwner, {
            adapter.changeHolding(it)
        })

        viewModel.changeList.observe(viewLifecycleOwner, {
            val changePosition = it.second
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
            val mediaModal = MediasModal(index, tips)
            mediaModal.show(fragmentManager, mediaModal.tag)
        }
    }
}