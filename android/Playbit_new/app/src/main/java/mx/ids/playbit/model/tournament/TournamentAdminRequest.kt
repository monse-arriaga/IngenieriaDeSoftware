package mx.ids.playbit.model.tournament

import mx.ids.playbit.model.User

/**
* Data class to create a TournamentAdminRequest instance and send it as request or manipulate
* its data before sending back with the apiservice
* @author Leonardo Aguilar Rodríguez
*  */
data class TournamentAdminRequest(
    val tournament: Tournament,
    val user: User
)