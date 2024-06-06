package mx.ids.playbit.api

import mx.ids.playbit.model.user.Participant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
* Participants api service with endpoints
* @author Leonardo Aguilar Rodríguez
*  */

interface ParticipantApiService {
    @GET("participant/all/")
    suspend fun findAll(): Response<List<Participant>>

    @POST("participant/create/")
    suspend fun createParticipant(@Body participants: List<Participant>): Response<Void>

    @POST("participant/edit/")
    suspend fun editParticipant(@Body participant: Participant): Response<Void>

    @POST("participant/delete/")
    suspend fun deleteParticipant(@Body participant: Participant): Response<Void>

    @GET("participant/find/{id}")
    suspend fun findParticipant(@Path("id") id: Int): Response<List<Participant>>
}
