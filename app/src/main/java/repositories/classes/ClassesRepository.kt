package repositories.classes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import repositories.FirebaseInstances.firestore

class ClassesRepository {

    @Suppress("UNCHECKED_CAST")
    fun getClasses(
        request: ClassesRequest,
        onSuccess: (List<ClassesResponse>) -> Unit,
        onError: (java.lang.Exception?) -> Unit
    ) {
        GlobalScope.launch(IO) {
            try {
                val classes = firestore
                    .collection("classes")
                    .document(request.id_class)
                    .get()
                    .await()

                val days = classes.get("days") as ArrayList<*>

                val result: List<ClassesResponse> = days.map { result ->
                    ClassesResponse().apply {
                        initService(result as Map<String, Any>)
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

