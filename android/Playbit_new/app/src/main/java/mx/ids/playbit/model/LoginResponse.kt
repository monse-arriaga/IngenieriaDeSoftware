package mx.ids.playbit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val id: Int,
    val username: String,
    val email: String,
    val roles: List<String>?,
    val accessToken: String,
    val tokenType: String
) : Parcelable
