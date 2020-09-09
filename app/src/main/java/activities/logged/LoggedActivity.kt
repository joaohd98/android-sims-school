package activities.logged

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.activity_logged.*

class LoggedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)
    }

//    private fun setupMenuAction() {
//        activity_logged_menu_button.setOnClickListener {
//            findNavController(R.id.nav_host_fragment_logged)
//                .navigate(R.id.action_tabsFragment_to_menuScreen)
//        }
//    }
//
//    fun enterTabs(viewPager: ViewPager) {
//        val sectionsPagerAdapter = LoggedPageAdapter(this, supportFragmentManager)
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = activity_logged_tabs
//        tabs.setupWithViewPager(viewPager)
//    }
//
//    fun enterMenu() {
//        activity_logged_tabs.visibility = View.GONE
//    }


}