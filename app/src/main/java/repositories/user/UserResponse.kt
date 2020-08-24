package repositories.user

import android.net.Uri
import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import repositories.FirebaseInstances
import repositories.FirebaseInstances.storage
import java.io.File

@Entity(tableName = "user")
data class UserResponse(
    @PrimaryKey var uid: String = "",
    var name: String = "",
    var rm: String = "",
    var id_class: String = "",
    var actual_class: String = "",
    var course: String = "",
    var profile_picture: String? = null,
    var cover_picture: String? = null
) {

    suspend fun initService(uid: String, result: DocumentSnapshot) {
        this.uid = uid

        coroutineScope {
            name = result.getString("name")!!
            rm = result.getString("rm")!!
            id_class = result.getString("id_class")!!
            actual_class = result.getString("actual_class")!!
            course = result.getString("course")!!

            val profilePicture = result.getString("profile_picture")!!
            val coverPicture = result.getString("cover_picture")!!

            val profilePictureRes = async { storage.reference.child(profilePicture).downloadUrl.await() }
            val coverPictureRes = async { storage.reference.child(coverPicture).downloadUrl.await() }

            cover_picture = coverPictureRes.await().toString()
            profile_picture = profilePictureRes.await().toString()
        }
    }

    suspend fun changeProfilePicture(file: File, userRepository: UserRepository, onComplete: (java.lang.Exception?) -> Unit) {
        coroutineScope {
            val refURL = "profile-pictures/${uid}.png"
            val pictureRef = storage.reference.child(refURL)

            try {
                pictureRef.putFile(Uri.fromFile(file)).await()!!
                val uri = pictureRef.downloadUrl.await()!!

                userRepository.changeProfile(uid, uri) {
                    if (it != null) {
                        onComplete(it)
                    }
                    else {
                        profile_picture = uri.toString()
                        onComplete(null)
                    }
                }
            } catch (exception : Exception) {
                onComplete(exception)
            }
        }
    }
}
