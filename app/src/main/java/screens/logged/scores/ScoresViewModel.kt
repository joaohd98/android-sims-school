package screens.logged.scores

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import repositories.RepositoryStatus
import repositories.scores.ScoresCourseResponse
import repositories.scores.ScoresRepository
import repositories.scores.ScoresRequest
import repositories.scores.ScoresResponse
import repositories.user.UserRepository
import repositories.user.UserResponse

class ScoresViewModel(application: android.app.Application): AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    val user: LiveData<UserResponse?>

    private val scoresRepository = ScoresRepository()
    val actualSemester: MutableLiveData<Int> by lazy {
        MutableLiveData(-1)
    }
    val statusScore: MutableLiveData<RepositoryStatus> by lazy {
        MutableLiveData(RepositoryStatus.LOADING)
    }
    val scores = mutableListOf<ScoresResponse>()

    init {
        user = userRepository.getUser()
    }

    fun callScores(isRetry: Boolean = false) {
        val user = user.value ?: return

        if(!isRetry && statusScore.value != RepositoryStatus.LOADING) {
            statusScore.value = RepositoryStatus.LOADING
        }

        scoresRepository.getScores(object: ScoresRequest {
            override val idUser: String = user.uid
        }, {
            actualSemester.value = it.size - 1
            scores.addAll(it)
            statusScore.value = RepositoryStatus.SUCCESS
        }) {
            statusScore.value = RepositoryStatus.FAILED
        }
    }

    fun getActualScores(): ArrayList<ScoresCourseResponse> {
        return if(scores.size > 0)
            scores[actualSemester.value!!].courses
        else
            arrayListOf()
    }
}