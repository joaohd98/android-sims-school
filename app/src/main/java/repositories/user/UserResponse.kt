package repositories.user

import android.net.Uri
import com.google.firebase.firestore.DocumentSnapshot
import repositories.FirebaseInstances.storage

class UserResponse(
    var uid: String = "",
    var name: String = "",
    var rm: String = "",
    var id_class: String = "",
    var actual_class: String = "",
    var course: String = "",
    var profile_picture: Uri? = null,
    var cover_picture: Uri? = null
) {

    fun initService(result: DocumentSnapshot) {
        uid = result.getString("uid")!!
        name = result.getString("name")!!
        rm = result.getString("rm")!!
        id_class = result.getString("id_class")!!
        actual_class = result.getString("actual_class")!!
        course = result.getString("course")!!

        val profilePicture = result.getString("profile_picture")!!
        storage.reference.child(profilePicture).downloadUrl.addOnCompleteListener {
            if(it.isSuccessful) {
                profile_picture = it.result
             }
        }

        val coverPicture = result.getString("cover_picture")!!
        storage.reference.child(coverPicture).downloadUrl.addOnCompleteListener {
            if(it.isSuccessful) {
                cover_picture = it.result
            }
        }

    }
}
