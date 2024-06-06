package mx.ids.playbit.model.user

/**
* Data class to create a SignupRequest instance and send it as request
 * @author Leonardo Aguilar Rodríguez
*  */
data class SignupRequest(
    val username: String,
    val email: String,
    val password: String,
    val bornDate: String,
    val role: Set<String>
)