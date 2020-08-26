package repositories.classes

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

class ClassesResponse(
    var data: String = "",
    var course: String = "",
    var weekDay: String = "",
    var hasClass: Boolean = false,
    var place: String = "",
    var teacher: String = "",
) {
    fun initService(result: Map<String, Any?>) {
            course = (result["course"] as? String ?: "")
            weekDay = (result["weekDay"] as? String ?: "")
            hasClass = (result["hasClass"] as? Boolean ?: false)
            place =  (result["place"] as? String ?: "")
            teacher = (result["teacher"] as? String ?: "")
    }
}
