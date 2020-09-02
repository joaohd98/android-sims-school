package screens.logged.classes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joao.simsschool.R
import repositories.RepositoryStatus
import repositories.calendar.CalendarRepository
import repositories.calendar.CalendarRequest
import repositories.calendar.CalendarResponse
import repositories.classes.ClassesRequest
import repositories.user.UserRepository
import repositories.user.UserResponse


class ClassesViewModel(application: android.app.Application): AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    val user: LiveData<UserResponse?>

    private val calendarRepository = CalendarRepository()
    val calendarMonths = mutableListOf<CalendarResponse>()
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
            calendarMonths.addAll(it)
            calendarStatus.value = RepositoryStatus.SUCCESS
        }) {
            calendarStatus.value = RepositoryStatus.FAILED
        }
    }
}