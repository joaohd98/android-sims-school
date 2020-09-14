package screens.logged.tabs.tips.modal_medias.adapter

import android.os.Bundle
import android.view.*
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

    fun changeHolding(isHolding: Boolean) {
        if(hasInitialized()) {
            binding.isHolding = isHolding
        }
    }

    fun changedMedia() {
        val tip = viewModel.getActualTip(position)

        binding.modalMediasItemProgressView.newCurrentPosition(tip.currentMediaPosition)
        binding.modalMediasItemFooterView.changeFooterLink(tip.getMedia().url)
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