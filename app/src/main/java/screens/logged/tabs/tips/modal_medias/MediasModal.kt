package screens.logged.tabs.tips.modal_medias

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasBinding
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_medias.adapter.MediasAdapter
import utils.CubeTransformer


class MediasModal(
    private val tips: ArrayList<TipsResponse>,
    private val index: Int
): DialogFragment() {
    private val viewModel: MediasViewModel by viewModels()
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
        val adapter =  MediasAdapter(context as FragmentActivity, tips) { value -> }

        binding.modalMediasViewPager.apply {
            this.adapter = adapter

            setCurrentItem(index, false)
            setPageTransformer(CubeTransformer())
        }
    }

    private fun initTouchListener() {
        binding.modalMediasViewPager.apply {
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when(state) {
                        ViewPager2.SCROLL_STATE_DRAGGING -> viewModel.changeSliding(true)
                        ViewPager2.SCROLL_STATE_SETTLING -> viewModel.changeSliding(false)
                        else -> { }
                    }
                }
            })

            getChildAt(0).setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        viewModel.changeHolding(true)
                        performClick()
                    }
                    MotionEvent.ACTION_UP -> {
                        viewModel.changeHolding(false)
                        performClick()

                    }
                    else -> {}
                }
                false
            }
        }
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