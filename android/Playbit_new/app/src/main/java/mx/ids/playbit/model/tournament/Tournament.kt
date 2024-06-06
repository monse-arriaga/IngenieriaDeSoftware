package mx.ids.playbit.model.tournament

/**
* Data class to create a tournament instance and send it as request or manipulate
* its data before sending back with the apiservice
* @author Leonardo Aguilar Rodríguez
*  */
data class Tournament(
    val name: String,
    val image: String,
    val players: Int,
    val description: String,
    val state: String,
    val tournamentType: String,
    val tournamentGame: String,
    val playersBT: Int,
    val date: String,
    val prize: Int,
    val inPlayers: Int,
    val time: String
)