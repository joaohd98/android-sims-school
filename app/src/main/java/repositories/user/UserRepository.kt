package repositories.user

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import repositories.FirebaseInstances.auth
import repositories.FirebaseInstances.firestore
import repositories.RepositoryStatus

object UserRepository {
    fun signIn(
        userRequest: UserRequest,
        onSuccess: (UserResponse) -> Unit,
        onError: (String?) -> Unit
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
                    .await();

                val userResponse = UserResponse()
                userResponse.initService(user.uid, result)

                GlobalScope.launch(Dispatchers.Main) {
                    onSuccess(userResponse)
                }
            }
            catch (exception : Exception) {
                val errorCode = (exception as FirebaseAuthException).errorCode

                GlobalScope.launch(Dispatchers.Main) {
                    onError(errorCode)
                }
            }
        }
    }
}