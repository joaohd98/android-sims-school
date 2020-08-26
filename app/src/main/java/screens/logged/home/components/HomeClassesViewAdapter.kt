package screens.logged.home.components

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.joao.simsschool.R


class HomeClassesViewAdapter(private val context: Context) : PagerAdapter() {

    private val pages = arrayOf(
        HomeClassesView(context),
        HomeClassesView(context),
        HomeClassesView(context),
    )

    fun showSkeleton() {
        pages[0].showSkeleton()
    }

    fun hideSkeleton() {
        pages[0].hideSkeleton()
    }


    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val position = position % pages.size
        collection.addView(pages[position])
        return pages[position]
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return Integer.MAX_VALUE;
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

}