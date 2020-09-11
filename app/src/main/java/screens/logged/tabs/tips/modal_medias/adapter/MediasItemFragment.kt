package screens.logged.tabs.tips.modal_medias.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemBinding
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.TipsViewModel

class MediasItemFragment(
    private val tip: TipsResponse,
    private val slideLeft: () -> Unit,
    private val slideRight: () -> Unit
): Fragment() {

    private val viewModel: MediasItemViewModel by viewModels {
        MediasItemViewModelFactory(tip.medias)
    }
    lateinit var binding: ModalMediasItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.modal_medias_item, container, false
        )

        return binding.root
    }

    companion object {
        fun newInstance(tip: TipsResponse, slideLeft: () -> Unit, slideRight: () -> Unit)
                = MediasItemFragment(tip, slideLeft, slideRight)
    }
}