package screens.logged.tips

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import repositories.RepositoryStatus
import repositories.scores.ScoresRepository
import repositories.scores.ScoresRequest
import repositories.scores.ScoresResponse
import repositories.tips.TipsRepository
import repositories.tips.TipsRequest
import repositories.tips.TipsResponse
import repositories.user.UserRepository
import repositories.user.UserResponse

class TipsViewModel(application: android.app.Application): AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    val user: LiveData<UserResponse?>

    private val tipsRepository = TipsRepository(application)
    val statusTips: MutableLiveData<RepositoryStatus> by lazy {
        MutableLiveData(RepositoryStatus.LOADING)
    }
    val tips = mutableListOf<TipsResponse>()

    init {
        user = userRepository.getUser()
    }

    fun callTips(isRetry: Boolean = false) {
        val user = user.value ?: return

        if(!isRetry && statusTips.value != RepositoryStatus.LOADING) {
            statusTips.value = RepositoryStatus.LOADING
        }

        tipsRepository.getTips(object: TipsRequest {
            override val idClass: String = user.id_class
        }, {
            tips.addAll(it)
            statusTips.value = RepositoryStatus.SUCCESS
        }) {
            statusTips.value = RepositoryStatus.FAILED
        }
    }
}