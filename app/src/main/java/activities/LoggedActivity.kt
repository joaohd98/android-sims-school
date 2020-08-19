package activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joao.simsschool.R

class LoggedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.show()

        setContentView(R.layout.activity_logged)
    }
}