package mx.ids.playbit.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JwtResponse(
    val token: String?,
    val id: Int?,
    val username: String?,
    val email: String?
) : Parcelable