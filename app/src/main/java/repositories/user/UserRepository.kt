package repositories.user

import android.R.attr
import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import repositories.AppDatabase
import repositories.FirebaseInstances
import repositories.FirebaseInstances.auth
import repositories.FirebaseInstances.firestore
import java.io.ByteArrayOutputStream


class UserRepository(application: Application) {
    private var userDao: UserDao

    init {
        val database = AppDatabase.invoke(application)
        userDao = database.userDao()
    }

    fun getUser() = userDao.getFirst()

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
            catch (exception: Exception) {

                GlobalScope.launch(Dispatchers.Main) {
                    onError(exception)
                }
            }
        }
    }

    fun changeProfile(
        bitmap: Bitmap,
        userResponse: UserResponse,
        onComplete: (java.lang.Exception?) -> Unit
    ) {
        GlobalScope.launch(IO) {
            val refURL = "profile-pictures/${userResponse.uid}.png"
            val pictureRef = FirebaseInstances.storage.reference.child(refURL)

            try {
                val bytes = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bytes)
                val data: ByteArray = bytes.toByteArray()

                pictureRef.putBytes(data).await()!!
                val uri = pictureRef.downloadUrl.await()!!.toString()

                val docUser = firestore
                    .collection("user")
                    .document(userResponse.uid)

                val fields = mapOf("profile_picture" to uri)
                val options= SetOptions.merge()

                docUser.set(fields, options).await()

                userResponse.profile_picture = uri
                userDao.updateProfilePicture(uri)

                GlobalScope.launch(Dispatchers.Main) {
                    onComplete(null)
                }
            }
            catch (exception : Exception) {
                GlobalScope.launch(Dispatchers.Main) {
                    onComplete(exception)
                }
            }
        }
    }
}

