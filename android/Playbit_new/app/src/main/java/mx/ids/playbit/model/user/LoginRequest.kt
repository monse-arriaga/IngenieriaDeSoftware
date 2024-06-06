package mx.ids.playbit.model.user

/**
* Data class to create a LoginRequest instance and send it as request
* @author Leonardo Aguilar Rodríguez
*  */
data class LoginRequest(
    val username: String,
    val password: String
)