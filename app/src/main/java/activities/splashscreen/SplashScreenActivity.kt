package activities.splashscreen

import activities.logged.LoggedActivity
import activities.guest.GuestActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import repositories.user.UserRepository
import repositories.user.UserResponse

class SplashScreenActivity : AppCompatActivity() {
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.user.observe(this, {
            val intent = Intent(this,
                if(it != null) LoggedActivity::class.java else GuestActivity::class.java
            )

            startActivity(intent)
            finish()
        })
    }


}
