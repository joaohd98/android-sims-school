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
): Fragment() {
    lateinit var binding: ModalMediasItemBinding
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
        setObserves()
    }

    private fun setProgressView() {
        val tip = viewModel.getActualTip(position)

        binding.modalMediasItemProgressView.apply {
            initProgressView(tip.getMediaSize(), tip.currentMedia)
        }
    }

    private fun setObserves() {
        viewModel.isHolding.observe(viewLifecycleOwner, {
            binding.isHolding = it
        })

        viewModel.changeList.observe(viewLifecycleOwner, {
            val changePosition = it.second
        })

        viewModel.actualIndex.observe(viewLifecycleOwner, {
        })
    }
}