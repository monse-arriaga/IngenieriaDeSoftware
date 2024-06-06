package mx.ids.playbit.api

import mx.ids.playbit.model.MessageResponse
import mx.ids.playbit.model.User
import mx.ids.playbit.model.user.EnrollDeleteRequest
import mx.ids.playbit.model.user.JwtResponse
import mx.ids.playbit.model.user.LoginRequest
import mx.ids.playbit.model.user.SignupRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
* User api service with endpoints
* @author Leonardo Aguilar Rodríguez
*  */
interface UserApiService {

    @POST("/user/create/")
    suspend fun createUser(@Body signupRequest: SignupRequest): MessageResponse

    @POST("/user/login/")
    suspend fun loginUser(@Body loginRequest: LoginRequest): JwtResponse?

    @POST("/user/enroll/{username}/{tournament_name}")
    suspend fun enrollUser(
        @Path("username") username: String,
        @Path("tournament_name") tournamentName: String
    ): MessageResponse

    @GET("/user/tournaments_enrolled/{userid}")
    suspend fun getUserTournaments(@Path("userid") userId: Int): List<String>

    @POST("/user/edit/")
    suspend fun editUser(@Body user: User): MessageResponse

    @POST("/user/delete_enrollment/")
    suspend fun deleteUserEnrollment(@Body enrollDeleteRequest: EnrollDeleteRequest): MessageResponse

    @GET("/user/findbyname/{username}")
    suspend fun findUserByName(@Path("username") username: String): List<User>

    @GET("/user/findbyid/{id}")
    suspend fun findUserById(@Path("id") id: Int): List<User>
}
