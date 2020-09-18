package activities.logged

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joao.simsschool.R
import utils.CacheVideoTemp

class LoggedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)
    }

    override fun onDestroy() {
        super.onDestroy()
        CacheVideoTemp.deleteAllCache(this)
    }
}