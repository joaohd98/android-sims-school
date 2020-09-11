package screens.logged.tabs.tips.modal_medias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasBinding
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_medias.adapter.MediasAdapter
import utils.CubeTransformer


class MediasModal(
    private val tips: ArrayList<TipsResponse>,
    private val index: Int
): DialogFragment() {
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

        val adapter = MediasAdapter(context as FragmentActivity, tips)

        binding.modalMediasViewPager.apply {
            this.adapter = adapter
            setCurrentItem(index, false)
            setPageTransformer(CubeTransformer())
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