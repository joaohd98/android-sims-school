package screens.logged.home

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import repositories.RepositoryStatus
import repositories.ads.AdsRepository
import repositories.ads.AdsResponse
import repositories.classes.ClassesRepository
import repositories.classes.ClassesRequest
import repositories.classes.ClassesResponse
import repositories.user.UserRepository
import repositories.user.UserResponse
import utils.ViewModelFromTab
import java.util.*


class HomeViewModel(application: android.app.Application): ViewModelFromTab(application) {
    private val userRepository = UserRepository(application)
    val user: LiveData<UserResponse?>
    val statusChangeProfile: MutableLiveData<RepositoryStatus> by lazy {
        MutableLiveData<RepositoryStatus>(RepositoryStatus.LOADING)
    }

    private val classesRepository = ClassesRepository()
    val classes = mutableListOf<ClassesResponse>()
    val actualClassIndex: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(
            Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1
        )
    }
    val statusClass: MutableLiveData<RepositoryStatus> by lazy {
        MutableLiveData<RepositoryStatus>(RepositoryStatus.LOADING)
    }

    private val adsRepository = AdsRepository()
    val ad: MutableLiveData<AdsResponse> by lazy {
        MutableLiveData()
    }
    val statusAds: MutableLiveData<RepositoryStatus> by lazy {
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

    fun callClasses(isRetry: Boolean = false) {
        val user = user.value ?: return

        if(!isRetry && statusClass.value != RepositoryStatus.LOADING) {
            statusClass.value = RepositoryStatus.LOADING
        }

        classesRepository.getClasses(object: ClassesRequest {
            override val id_class: String = user.id_class
        }, {
            classes.addAll(it)
            statusClass.value = RepositoryStatus.SUCCESS
        }) {
            statusClass.value = RepositoryStatus.FAILED
        }
    }


    fun callAds(isRetry: Boolean = false) {
        if(!isRetry && statusClass.value != RepositoryStatus.LOADING) {
            statusAds.value = RepositoryStatus.LOADING
        }

        adsRepository.getRandomAd({
            ad.value = it
            statusAds.value = RepositoryStatus.SUCCESS
        }) {
            statusAds.value = RepositoryStatus.FAILED
        }
    }
}