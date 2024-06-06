package mx.ids.playbit.repository

import mx.ids.playbit.api.ParticipantApiService
import mx.ids.playbit.model.user.Participant
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Participant Repository injects the api service needed
 * @author Leonardo Aguilar Rodríguez
 *  */
@Singleton
class ParticipantRepository @Inject constructor(
    private val api: ParticipantApiService
) {

    suspend fun findAll(): List<Participant>? {
        val response = api.findAll()
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun createParticipant(participants: List<Participant>): Boolean {
        val response = api.createParticipant(participants)
        return response.isSuccessful
    }

    suspend fun editParticipant(participant: Participant): Boolean {
        val response = api.editParticipant(participant)
        return response.isSuccessful
    }

    suspend fun deleteParticipant(participant: Participant): Boolean {
        val response = api.deleteParticipant(participant)
        return response.isSuccessful
    }

    suspend fun findParticipant(id: Int): List<Participant>? {
        val response = api.findParticipant(id)
        return if (response.isSuccessful) response.body() else null
    }
}
