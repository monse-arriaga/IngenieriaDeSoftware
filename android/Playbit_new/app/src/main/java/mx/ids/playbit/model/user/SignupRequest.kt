package mx.ids.playbit.model.user

data class SignupRequest(
    val username: String,
    val email: String,
    val password: String
)