package mx.ids.playbit.repository

import mx.ids.playbit.api.UserApiService
import mx.ids.playbit.model.MessageResponse
import mx.ids.playbit.model.User
import mx.ids.playbit.model.user.EnrollDeleteRequest
import mx.ids.playbit.model.user.JwtResponse
import mx.ids.playbit.model.user.LoginRequest
import mx.ids.playbit.model.user.SignupRequest
import javax.inject.Inject
import javax.inject.Singleton

/**
 * User Repository injects the api service needed
 * @author Leonardo Aguilar Rodríguez
 *  */
@Singleton
class UserRepository @Inject constructor(private val userApiService: UserApiService) {

    suspend fun createUser(signupRequest: SignupRequest): MessageResponse {
        return userApiService.createUser(signupRequest)
    }

    suspend fun loginUser(loginRequest: LoginRequest): JwtResponse? {
        return userApiService.loginUser(loginRequest)
    }

    suspend fun enrollUser(username: String, tournamentName: String): MessageResponse {
        return userApiService.enrollUser(username, tournamentName)
    }

    suspend fun getUserTournaments(userId: Int): List<String> {
        return userApiService.getUserTournaments(userId)
    }

    suspend fun editUser(user: User): MessageResponse {
        return userApiService.editUser(user)
    }

    suspend fun deleteUserEnrollment(enrollDeleteRequest: EnrollDeleteRequest): MessageResponse {
        return userApiService.deleteUserEnrollment(enrollDeleteRequest)
    }

    suspend fun findUserByName(username: String): List<User> {
        return userApiService.findUserByName(username)
    }

    suspend fun findUserById(id: Int): List<User> {
        return userApiService.findUserById(id)
    }
}
