package mx.ids.playbit.model

/**
* Data class to create a User instance and send it as request or manipulate
* its data before sending back with the apiservice
 * @author Leonardo Aguilar Rodríguez
*  */
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val bornDate: String,
    val bio: String
)
