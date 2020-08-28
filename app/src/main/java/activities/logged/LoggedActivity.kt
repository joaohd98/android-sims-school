package activities.logged

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.joao.simsschool.R

class LoggedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        val sectionsPagerAdapter = LoggedPageAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.activity_logged_view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.activity_logged_tabs)
        tabs.setupWithViewPager(viewPager)
    }
}