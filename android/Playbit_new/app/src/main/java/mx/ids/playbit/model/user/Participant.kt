package mx.ids.playbit.model.user

import com.google.gson.annotations.SerializedName

/**
* Data class to create a Participant instance and send it as request or manipulate
* its data before sending back with the apiservice
 * @author Leonardo Aguilar Rodríguez
*  */
data class Participant(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String,

    @SerializedName("tournament_id")
    val tournamentId: String
)