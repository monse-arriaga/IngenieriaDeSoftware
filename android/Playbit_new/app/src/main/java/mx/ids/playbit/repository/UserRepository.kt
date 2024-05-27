package mx.ids.playbit.repository

import mx.ids.playbit.api.UserApiService
import mx.ids.playbit.model.LoginResponse
import mx.ids.playbit.model.MessageResponse
import mx.ids.playbit.model.User
import mx.ids.playbit.model.user.JwtResponse
import mx.ids.playbit.model.user.LoginRequest
import mx.ids.playbit.model.user.SignupRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userApiService: UserApiService) {



    suspend fun createUser(signupRequest: SignupRequest): MessageResponse? {
        val response = userApiService.createUser(signupRequest)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun loginUser(loginRequest: LoginRequest): JwtResponse? {
        val response = userApiService.loginUser(loginRequest)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun enrollUser(username: String, tournamentName: String): MessageResponse? {
        val response = userApiService.enrollUser(username, tournamentName)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getUserTournaments(userId: Int): List<String>? {
        val response = userApiService.getUserTournaments(userId)
        return if (response.isSuccessful) response.body() else null
    }
}
