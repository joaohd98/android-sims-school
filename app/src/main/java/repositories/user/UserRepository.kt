package repositories.user

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import repositories.AppDatabase
import repositories.FirebaseInstances.auth
import repositories.FirebaseInstances.firestore
import repositories.RepositoryStatus

class UserRepository(application: Application) {
    private var userDao: UserDao

    init {
        val database = AppDatabase.invoke(application)
        userDao = database.userDao()
    }

    fun signIn(
        userRequest: UserRequest,
        onSuccess: (UserResponse) -> Unit,
        onError: (java.lang.Exception?) -> Unit
    ) {
        GlobalScope.launch(IO) {
            try {
                val authResult = auth
                    .signInWithEmailAndPassword(userRequest.email, userRequest.password)
                    .await()

                val user = authResult!!.user!!
                val result = firestore
                    .collection("user")
                    .document(user.uid)
                    .get()
                    .await()

                val userResponse = UserResponse()
                userResponse.initService(user.uid, result)
                userDao.insert(userResponse)

                GlobalScope.launch(Dispatchers.Main) {
                    onSuccess(userResponse)
                }
            }
            catch (exception : Exception) {

                GlobalScope.launch(Dispatchers.Main) {
                    onError(exception)
                }
            }
        }
    }
}