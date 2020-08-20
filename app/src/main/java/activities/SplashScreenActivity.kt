package activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.postDelayed
import com.joao.simsschool.R
import repositories.AppDatabase
import java.security.AccessController.getContext

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        /*
         * Init local database
         */
        val instance = AppDatabase.invoke(context = this)

        val user = instance.userDao().getFirst().value
        val intent = Intent(this,
           if(user != null) LoggedActivity::class.java else GuestActivity::class.java
        )
        startActivity(intent)
        finish()

    }

}