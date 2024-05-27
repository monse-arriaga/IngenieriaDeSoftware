package mx.ids.playbit.api

import mx.ids.playbit.model.LoginResponse
import mx.ids.playbit.model.MessageResponse
import mx.ids.playbit.model.User
import mx.ids.playbit.model.user.JwtResponse
import mx.ids.playbit.model.user.LoginRequest
import mx.ids.playbit.model.user.SignupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {

    @POST("user/create/")
    suspend fun createUser(@Body user: SignupRequest): Response<MessageResponse>

    @POST("user/login/")
    suspend fun loginUser(@Body user: LoginRequest): Response<JwtResponse>

    @POST("user/enroll/{username}/{tournament_name}")
    suspend fun enrollUser(
        @Path("username") username: String,
        @Path("tournament_name") tournamentName: String
    ): Response<MessageResponse>

    @GET("user/tournaments_enrolled/{userid}")
    suspend fun getUserTournaments(@Path("userid") userId: Int): Response<List<String>>
}