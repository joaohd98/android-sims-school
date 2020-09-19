package screens.logged.classes

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import repositories.RepositoryStatus
import repositories.calendar.CalendarRepository
import repositories.calendar.CalendarRequest
import repositories.calendar.CalendarResponse
import repositories.user.UserRepository
import repositories.user.UserResponse
import utils.ViewModelFromTab


class ClassesViewModel(application: android.app.Application): ViewModelFromTab(application) {
    private val userRepository = UserRepository(application)
    val user: LiveData<UserResponse?>

    private val calendarRepository = CalendarRepository()
    val calendarResponse: MutableLiveData<CalendarResponse> by lazy {
        MutableLiveData()
    }
    val calendarStatus: MutableLiveData<RepositoryStatus> by lazy {
        MutableLiveData<RepositoryStatus>(RepositoryStatus.LOADING)
    }
    init {
        user = userRepository.getUser()
    }

    fun callCalendar(isRetry: Boolean = false) {
        val user = user.value ?: return

        if(!isRetry && calendarStatus.value != RepositoryStatus.LOADING) {
            calendarStatus.value = RepositoryStatus.LOADING
        }

        calendarRepository.getCalendar(object: CalendarRequest {
            override val idClass: String = user.id_class
        }, {
            calendarResponse.value = it
            calendarStatus.value = RepositoryStatus.SUCCESS
        }) {
            calendarStatus.value = RepositoryStatus.FAILED
        }
    }
}