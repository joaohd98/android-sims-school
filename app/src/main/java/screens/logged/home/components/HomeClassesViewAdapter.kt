package screens.logged.home.components

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import repositories.classes.ClassesResponse

class HomeClassesViewAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private var isLoading = true
    private var classes: ArrayList<ClassesResponse> = arrayListOf()
    private var fragments: ArrayList<HomeClassesView> = arrayListOf()

    private fun isComplete() = fragments.size == itemCount

    override fun getItemCount(): Int {
        return if(isLoading) 1 else Integer.MAX_VALUE
    }

    override fun createFragment(position: Int): Fragment {
        val size = classes.size
        val index = if(size > 0) position % size else 0

        val fragment = HomeClassesView(isLoading, if(index < classes.size) classes[index] else null)

        fragments.add(fragment)

        return fragment
    }

    fun setClasses(classes: ArrayList<ClassesResponse>) {
        this.classes = classes
        isLoading = false
    }

    fun setLoading() {
        this.classes = arrayListOf()
        isLoading = true
    }
}
