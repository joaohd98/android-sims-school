package screens.logged.home.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.joao.simsschool.R

class HomeClassesViewAdapter(private val context: Context) : PagerAdapter() {

    private val pages = arrayOf(
        Pair(HomeClassesView(context), "Scores"),
        Pair(HomeClassesView(context), "Classes"),
        Pair(HomeClassesView(context), "Tips")
    )

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val position = position % pages.size
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.view_home_classes, collection, false) as ViewGroup
        collection.addView(layout)
        return layout
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

    override fun getPageTitle(position: Int): CharSequence {
        return pages[position].second
    }
}