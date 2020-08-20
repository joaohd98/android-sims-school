package activities.splashscreen

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import repositories.user.UserRepository
import repositories.user.UserResponse

class SplashScreenViewModel(application: android.app.Application): AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    val user: LiveData<UserResponse?>

    init {
        user = userRepository.getUser()
    }
}
