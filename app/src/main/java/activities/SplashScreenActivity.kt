package activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.postDelayed
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.joao.simsschool.R
import com.squareup.okhttp.internal.Internal.instance
import repositories.AppDatabase
import repositories.user.UserRepository
import repositories.user.UserResponse
import java.security.AccessController.getContext

class SplashScreenActivity : AppCompatActivity() {
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        viewModel.user.observe(this, Observer {
            val intent = Intent(this,
                if(it != null) LoggedActivity::class.java else GuestActivity::class.java
            )

            startActivity(intent)
            finish()
        })
    }


}
class SplashScreenViewModel(application: android.app.Application): AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    val user: LiveData<UserResponse?>

    init {
        user = userRepository.getUser()
    }
}
