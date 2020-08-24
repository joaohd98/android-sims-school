package screens.logged.home

import android.graphics.Bitmap
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import repositories.RepositoryStatus
import repositories.user.UserRepository
import repositories.user.UserResponse


class HomeViewModel(application: android.app.Application): AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    private val userDao = UserRepository(application)

    val user: LiveData<UserResponse?>

    val statusChangeProfile: MutableLiveData<RepositoryStatus> by lazy {
        MutableLiveData<RepositoryStatus>(RepositoryStatus.LOADING)
    }

    init {
        user = userRepository.getUser()
    }

    fun changeProfilePicture(bitmap: Bitmap) {
        val user = user.value ?: return

        statusChangeProfile.value = RepositoryStatus.LOADING
        userRepository.changeProfile(bitmap, user) {
            if(it != null) {
                statusChangeProfile.value = RepositoryStatus.FAILED
            }
            else {
                statusChangeProfile.value = RepositoryStatus.SUCCESS
            }
        }
    }

}