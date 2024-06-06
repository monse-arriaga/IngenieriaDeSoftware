package mx.ids.playbit.repository

import mx.ids.playbit.api.TournamentApiService
import mx.ids.playbit.model.tournament.Tournament
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Tournament Repository
 * @author Leonardo Aguilar Rodríguez
 *  */
@Singleton
class TournamentRepository @Inject constructor(private val tournamentApiService: TournamentApiService) {

    suspend fun createTournament(tournament: Tournament): Boolean {
        val response = tournamentApiService.createTournament(tournament)
        return response.isSuccessful
    }

    suspend fun getAllTournaments(): List<Tournament> {
        val response = tournamentApiService.getAllTournaments()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }
    }

    suspend fun findTournamentsByName(name: String): List<Tournament> {
        val response = tournamentApiService.findTournamentsByName(name)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }
    }

    suspend fun editTournament(tournament: Tournament): Boolean {
        val response = tournamentApiService.editTournament(tournament)
        return response.isSuccessful
    }

    suspend fun deleteTournament(tournament: Tournament): Boolean {
        val response = tournamentApiService.deleteTournament(tournament)
        return response.isSuccessful
    }
}
