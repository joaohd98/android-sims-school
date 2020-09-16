package repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object FirebaseInstances {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()

    init {
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()

        firestore.firestoreSettings = settings
    }

    suspend fun getURLFromMedia(url: String): String? {
        if(url == "")
            return null

        return withContext(Dispatchers.IO) {
            storage.reference.child(url).downloadUrl.await()
        }.toString()
    }

}