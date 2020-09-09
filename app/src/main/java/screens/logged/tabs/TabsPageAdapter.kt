package screens.logged.tabs

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import screens.logged.tabs.classes.ClassesScreen
import screens.logged.tabs.home.HomeScreen
import screens.logged.tabs.scores.ScoresScreen
import screens.logged.tabs.tips.TipsScreen

class TabsPageAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = arrayOf(
        Pair(TipsScreen(), "Tips"),
        Pair(ClassesScreen(), "Classes"),
        Pair(ScoresScreen(), "Scores"),
        Pair(HomeScreen(), "Home"),
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].first
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].second
    }

    override fun getCount(): Int {
        return pages.size
    }
}