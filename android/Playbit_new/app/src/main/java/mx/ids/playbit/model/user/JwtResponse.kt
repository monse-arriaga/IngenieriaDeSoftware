package mx.ids.playbit.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
/**
* Data class to create a JwtResponse instance and send it as request or manipulate
* its data before sending back with the apiservice
* @author Leonardo Aguilar Rodríguez
*  */
@Parcelize
data class JwtResponse(
    val token: String?,
    val id: Int?,
    val username: String?,
    val email: String?
) : Parcelable