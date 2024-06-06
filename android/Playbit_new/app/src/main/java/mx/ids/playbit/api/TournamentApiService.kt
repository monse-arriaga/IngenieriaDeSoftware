package mx.ids.playbit.api

import mx.ids.playbit.model.tournament.Tournament
import mx.ids.playbit.model.tournament.TournamentAdminRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
* Tournament api service with endpoints
* @author Leonardo Aguilar Rodríguez
*  */
interface TournamentApiService {
    @POST("tournament/create/")
    suspend fun createTournament(@Body tournament: Tournament): Response<Void>

    @GET("tournament/all/")
    suspend fun getAllTournaments(): Response<List<Tournament>>

    @GET("tournament/find/{name}")
    suspend fun findTournamentsByName(@Path("name") name: String): Response<List<Tournament>>

    @POST("tournament/edit/")
    suspend fun editTournament(@Body tournament: Tournament): Response<Void>

    @POST("tournament/delete/")
    suspend fun deleteTournament(@Body tournament: Tournament): Response<Void>
}
