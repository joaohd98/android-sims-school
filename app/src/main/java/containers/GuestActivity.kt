package containers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joao.simsschool.R

class GuestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_guest)
    }
}
