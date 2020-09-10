package screens.logged.menu

import android.app.Activity
import android.content.Context
import androidx.core.app.ShareCompat
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.joao.simsschool.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import repositories.FirebaseInstances
import repositories.user.UserRepository

class MenuViewModel(application: android.app.Application): AndroidViewModel(application)  {
    private val userRepository = UserRepository(application)

    fun share(activity: Activity) {
        ShareCompat.IntentBuilder
            .from(activity)
            .setText("""
                Hey,

			    Sims School is a simple Android APP, that I created with so much care

			    See the source code at: https://github.com/joaohd98/android-sims-school
            """.trimIndent())
            .setType("text/plain")
            .setChooserTitle("Sims School")
            .startChooser()
    }

    fun logout() {
        FirebaseInstances.auth.signOut()
        userRepository.logout()
    }
}