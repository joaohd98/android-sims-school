package screens.logged.home

import android.graphics.Bitmap
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import repositories.RepositoryStatus
import repositories.classes.ClassesRepository
import repositories.classes.ClassesRequest
import repositories.classes.ClassesResponse
import repositories.user.UserRepository
import repositories.user.UserResponse


class HomeViewModel(application: android.app.Application): AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    val user: LiveData<UserResponse?>
    val statusChangeProfile: MutableLiveData<RepositoryStatus> by lazy {
        MutableLiveData<RepositoryStatus>(RepositoryStatus.LOADING)
    }

    private val classesRepository = ClassesRepository()
    val classes = mutableListOf<ClassesResponse>()
    val statusClass: MutableLiveData<RepositoryStatus> by lazy {
        MutableLiveData<RepositoryStatus>(RepositoryStatus.LOADING)
    }

    init {
        user = userRepository.getUser()
    }

    fun callClasses() {
        val user = user.value ?: return

        if(statusClass.value != RepositoryStatus.LOADING) {
            statusClass.value = RepositoryStatus.LOADING
        }

        classesRepository.getClasses(object: ClassesRequest {
            override val id_class: String = user.id_class
        }, {
            statusClass.value = RepositoryStatus.SUCCESS
            classes.addAll(it)
        }) {
            statusClass.value = RepositoryStatus.FAILED
        }
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