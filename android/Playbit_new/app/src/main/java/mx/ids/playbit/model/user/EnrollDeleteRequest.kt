package mx.ids.playbit.model.user

import mx.ids.playbit.model.User
import mx.ids.playbit.model.tournament.Tournament

/**
 * Data class for EnrollDeleteRequest
 * @author Leonardo Aguilar Rodríguez
 * */
data class EnrollDeleteRequest(
    val user: User,
    val tournament: Tournament
)