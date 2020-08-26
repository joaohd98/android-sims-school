package screens.logged.home.components

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class HomeClassesViewAdapter(
    private val context: Context,
    private val pages: List<HomeClassesView> = listOf(HomeClassesView(context))
) : PagerAdapter() {

    fun showSkeleton() {
        pages[0].showSkeleton()
    }

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val index = position % pages.size
        collection.addView(pages[index])
        return pages[index]
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return pages.size;
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}