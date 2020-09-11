package screens.logged.tabs.tips.modal_medias.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemBinding
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_medias.MediasViewModel

class MediasItemFragment(
    private val tip: TipsResponse,
    private val slideLeft: () -> Unit,
    private val slideRight: () -> Unit
): Fragment() {
    lateinit var binding: ModalMediasItemBinding

    private val mediasViewModel: MediasViewModel by activityViewModels()
    private val viewModel: MediasItemViewModel by viewModels()

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

        setOnTouchListener()
    }

    private fun setOnTouchListener() {
        mediasViewModel.isHolding.observe(viewLifecycleOwner, {
            binding.isHolding = it
        })
    }

}