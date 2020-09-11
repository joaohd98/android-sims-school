package screens.logged.tabs.tips.modal_medias.adapter

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasItemBinding
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_medias.MediasViewModel
import screens.logged.tabs.tips.modal_medias.MediasViewModelFactory

class MediasItemFragment(
    tips: ArrayList<TipsResponse>,
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

        setObserves()
    }

    private fun setObserves() {
        viewModel.isHolding.observe(viewLifecycleOwner, {
            binding.isHolding = it
        })

        viewModel.changeList.observe(viewLifecycleOwner, {
            val changePosition = it.second

            if(changePosition == position) {
                Log.d("aaa position", viewModel.tips[position].getMedia().url)
            }
        })
    }

}