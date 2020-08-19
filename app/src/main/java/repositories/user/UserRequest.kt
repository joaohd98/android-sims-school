package repositories.user

interface UserRequest {
    val email: String
    val password: String
}