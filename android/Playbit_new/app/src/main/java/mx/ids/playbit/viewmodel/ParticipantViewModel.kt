package mx.ids.playbit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.ids.playbit.model.user.Participant
import mx.ids.playbit.repository.ParticipantRepository
import mx.ids.playbit.utils.OperationState
import mx.ids.playbit.utils.OperationType
import javax.inject.Inject
/**
 * ParticipantViewModel communication bridge between view, model and repository
 * @author Leonardo Aguilar Rodríguez
 *  */
@HiltViewModel
class ParticipantViewModel @Inject constructor(
    private val repository: ParticipantRepository
) : ViewModel() {

    private val _participants = MutableLiveData<List<Participant>?>()
    val participants: MutableLiveData<List<Participant>?> get() = _participants

    private val _randomParticipants = MutableLiveData<List<Participant>?>()
    val randomParticipants: MutableLiveData<List<Participant>?> get() = _randomParticipants

    private val _operationState = MutableLiveData<OperationState>()
    val operationState: LiveData<OperationState> get() = _operationState

    fun setOperationState(state: OperationState) {
        _operationState.value = state
    }


    fun findAll() {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val participants = repository.findAll()
                _participants.value = participants

                if (participants?.size!! > 4) {
                    _randomParticipants.value = participants.shuffled().take(4)
                } else {
                    _randomParticipants.value = null
                }
                _operationState.value = OperationState.Success(OperationType.GETALL)
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.GETALL)
            }
        }
    }

    fun createParticipant(participants: List<Participant>) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val success = repository.createParticipant(participants)
                if (success) {
                    _operationState.value = OperationState.Success(OperationType.SUBSCRIBE)
                } else {
                    _operationState.value =
                        OperationState.Error("Error al crear participante", OperationType.SUBSCRIBE)
                }
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.SUBSCRIBE)
            }
        }
    }

    fun editParticipant(participant: Participant) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val success = repository.editParticipant(participant)
                if (success) {
                    _operationState.value = OperationState.Success(OperationType.EDIT)
                } else {
                    _operationState.value =
                        OperationState.Error("Error al editar participante", OperationType.EDIT)
                }
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.EDIT)
            }
        }
    }

    fun deleteParticipant(participant: Participant) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val success = repository.deleteParticipant(participant)
                if (success) {
                    _operationState.value = OperationState.Success(OperationType.DELETE)
                } else {
                    _operationState.value =
                        OperationState.Error("Error al eliminar participante", OperationType.DELETE)
                }
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.DELETE)
            }
        }
    }

    fun findParticipant(id: Int) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val participants = repository.findParticipant(id)
                _participants.value = participants
                _operationState.value = OperationState.Success(OperationType.FIND_BY_ID)
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.FIND_BY_ID)
            }
        }
    }
}