package activities.logged

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import screens.logged.classes.ClassesScreen
import screens.logged.home.HomeScreen
import screens.logged.scores.ScoresScreen
import screens.logged.tips.TipsFragment


class LoggedPageAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = arrayOf(
        Pair(ClassesScreen(), "Classes"),
        Pair(ScoresScreen(), "Scores"),
        Pair(HomeScreen(), "Home"),
        Pair(TipsFragment(), "Tips")
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