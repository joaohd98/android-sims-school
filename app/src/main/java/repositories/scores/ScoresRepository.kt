package repositories.scores

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import repositories.FirebaseInstances.firestore

class ScoresRepository {

    @Suppress("UNCHECKED_CAST")
    fun getScores(
        request: ScoresRequest,
        onSuccess: (List<ScoresResponse>) -> Unit,
        onError: (java.lang.Exception?) -> Unit
    ) {
        GlobalScope.launch(IO) {
            try {
                val classes = firestore
                    .collection("score")
                    .document(request.idUser)
                    .get()
                    .await()

                val semesters = classes.get("semesters") as ArrayList<*>


                val result: List<ScoresResponse> = semesters.map { it ->
                    Log.d("aaa", "a")

                    ScoresResponse().apply {
                        initService(it as Map<String, Any>)
                    }
                }


                GlobalScope.launch(Dispatchers.Main) {
                    onSuccess(result)
                }
            }
            catch (exception: Exception) {
                GlobalScope.launch(Dispatchers.Main) {
                    onError(exception)
                }
            }
        }
    }
}

