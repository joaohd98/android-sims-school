package screens.logged.tabs.tips.modal_all_tips.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalAllTipsItemBinding
import repositories.tips.TipsResponse

class AllTipsItem(private val tip: TipsResponse): Fragment() {
    lateinit var binding: ModalAllTipsItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.modal_all_tips_item, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.modalAllTipsItemText.text = tip.name
    }

    companion object {
        fun newInstance(tip: TipsResponse) = AllTipsItem(tip)
    }
}