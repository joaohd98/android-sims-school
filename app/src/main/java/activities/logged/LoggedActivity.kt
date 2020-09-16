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
import utils.CacheVideoTemp

class LoggedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)
    }

    override fun onStop() {
        super.onStop()

        CacheVideoTemp.deleteAllCache(this)
    }
}