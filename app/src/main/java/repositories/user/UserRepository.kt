package repositories.user

import com.google.firebase.auth.FirebaseAuthException
import repositories.FirebaseInstances.auth
import repositories.FirebaseInstances.db

object UserRepository {

    fun signIn(
        userRequest: UserRequest,
        onSuccess: (UserResponse) -> Unit,
        onError: (String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(userRequest.email, userRequest.password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser!!

                val docUser = db.collection("user").document(user.uid)

                 docUser.get().addOnSuccessListener { result ->
                     val userResponse = UserResponse()
                     userResponse.initService(result)
                     onSuccess(userResponse)
                 }
                 .addOnFailureListener {
                     onError(null)
                 }
            }
            else {

                val errorCode = (task.exception as FirebaseAuthException).errorCode
                onError(errorCode)
            }
        }
    }
}