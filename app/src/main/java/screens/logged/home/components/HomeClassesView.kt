package screens.logged.home.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewHomeClassesBinding
import repositories.classes.ClassesResponse
import utils.addSkeleton
import utils.addSkeletonAllElementsInner

class HomeClassesView(
    private val isLoading: Boolean,
    private val actualClass: ClassesResponse? = null
): Fragment() {
    private lateinit var binding: ViewHomeClassesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.view_home_classes, container, false
        )

        if(!isLoading && actualClass != null) {
            setClass(actualClass)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(isLoading) {
            showSkeleton()
        }
    }

    fun showSkeleton() {
        binding.viewHomeClassesFormattedData.addSkeleton()
        binding.viewHomeClassesLinearLayoutText.addSkeletonAllElementsInner()
    }

    private fun hideSkeleton() {
//        binding.viewHomeClassesFormattedData.removeSkeleton()
//        binding.viewHomeClassesLinearLayoutText.removeSkeletonAllElementsInner()
    }

    private fun setClass(actualClass: ClassesResponse) {
        if(!actualClass.hasClass) {
            binding.viewHomeClassesSwitcher.showNext()
        }

        binding.actualClass = actualClass
        hideSkeleton()
    }
}
