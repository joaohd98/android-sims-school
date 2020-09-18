package activities.splashscreen

import activities.logged.LoggedActivity
import activities.guest.GuestActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import repositories.FirebaseInstances
import repositories.user.UserRepository
import repositories.user.UserResponse
import utils.CacheVideoTemp

class SplashScreenActivity : AppCompatActivity() {
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.user.observe(this, {
            val firebaseUser = FirebaseInstances.auth.currentUser

            val intent = Intent(this,
                if(it != null && firebaseUser != null) LoggedActivity::class.java else GuestActivity::class.java
            )

            startActivity(intent)
            finish()
        })
    }
}
