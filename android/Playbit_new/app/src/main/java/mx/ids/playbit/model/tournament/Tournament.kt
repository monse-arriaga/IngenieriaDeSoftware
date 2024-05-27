package mx.ids.playbit.model.tournament

data class Tournament(
    val name: String,
    val players: Int,
    val description: String,
    val state: String,
    val tournamentType: String,
    val tournamentGame: String,
    val playersBT: Int,
    val image: String,
    val date: String,
    val inPlayers: Int,
    val prize: Int,
    val time: String
)