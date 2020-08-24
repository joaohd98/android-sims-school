package screens.logged.home

import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import repositories.RepositoryStatus
import repositories.user.UserRepository
import repositories.user.UserResponse
import java.io.File

class HomeViewModel(application: android.app.Application): AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    val user: LiveData<UserResponse?>

    init {
        user = userRepository.getUser()
    }

    fun changeProfilePicture(bitmap: Bitmap) {
        val user = user.value ?: return

        userRepository.changeProfile(bitmap, user) {

        }

    }

}