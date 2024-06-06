package mx.ids.playbit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.ids.playbit.model.tournament.Tournament
import mx.ids.playbit.repository.TournamentRepository
import mx.ids.playbit.utils.OperationState
import mx.ids.playbit.utils.OperationType
import javax.inject.Inject
/**
 * TournamentViewModel communication bridge between view, model and repository
 * @author Leonardo Aguilar Rodríguez
 *  */
@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val repository: TournamentRepository
) : ViewModel() {

    private val _operationState = MutableLiveData<OperationState>()
    val operationState: LiveData<OperationState> get() = _operationState

    fun setOperationState(state: OperationState) {
        _operationState.value = state
    }

    private val _selectedImg = MutableLiveData<String>()
    val selectedImg: LiveData<String> = _selectedImg

    fun setSelectedImg(imageUrl: String) {
        _selectedImg.postValue(imageUrl)
    }

    private val _tournaments = MutableLiveData<List<Tournament>>()
    val tournaments: LiveData<List<Tournament>> get() = _tournaments

    private val _selectedTournament = MutableLiveData<List<Tournament>?>()
    val selectedTournament: MutableLiveData<List<Tournament>?> get() = _selectedTournament

    fun setSelectedTournament(tournaments: List<Tournament>?) {
        _selectedTournament.value = tournaments
    }

    private val _randomTournaments = MutableLiveData<List<Tournament>?>()
    val randomTournaments: MutableLiveData<List<Tournament>?> get() = _randomTournaments


    private val _operationSuccess = MutableLiveData<Boolean>()
    val operationSuccess: LiveData<Boolean> get() = _operationSuccess

    fun createTournament(tournament: Tournament) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val success = repository.createTournament(tournament)
                if (success) {
                    _operationState.value = OperationState.Success(OperationType.CREATE)
                } else {
                    _operationState.value =
                        OperationState.Error("Error al crear torneo", OperationType.CREATE)
                }
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.CREATE)
            }
        }
    }

    fun getAllTournaments() {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val tournamentsList = repository.getAllTournaments()
                _tournaments.value = tournamentsList
                if (tournamentsList.size > 5) {
                    _randomTournaments.value = tournamentsList.shuffled().take(5)
                } else {
                    _randomTournaments.value = null
                }
                _operationState.value = OperationState.Success(OperationType.GETALL)
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.GETALL)
            }
        }
    }

    fun findTournamentsByName(name: String) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val tournamentsList = repository.findTournamentsByName(name)
                _selectedTournament.value = tournamentsList
                _operationState.value = OperationState.Success(OperationType.GETBYNAME)
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.GETBYNAME)
            }
        }
    }

    fun editTournament(tournament: Tournament) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val success = repository.editTournament(tournament)
                if (success) {
                    _operationState.value = OperationState.Success(OperationType.EDIT)
                } else {
                    _operationState.value =
                        OperationState.Error("Error al editar torneo", OperationType.EDIT)
                }
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.EDIT)
            }
        }
    }

    fun deleteTournament(tournament: Tournament) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val success = repository.deleteTournament(tournament)
                if (success) {
                    _operationState.value = OperationState.Success(OperationType.DELETE)
                } else {
                    _operationState.value =
                        OperationState.Error("Error al eliminar torneo", OperationType.DELETE)
                }
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.DELETE)
            }
        }
    }

    private val _tournamentDate = MutableLiveData<String>()
    val tournamentDate: LiveData<String> get() = _tournamentDate

    private val _tournamentTime = MutableLiveData<String>()
    val tournamentTime: LiveData<String> get() = _tournamentTime

    fun setTournamentDate(date: String) {
        _tournamentDate.value = date
    }

    fun setTournamentTime(time: String) {
        _tournamentTime.value = time
    }

    private val _selectedType = MutableLiveData<String>()
    val selectedType: LiveData<String> get() = _selectedType

    fun setSelectedType(type: String) {
        _selectedType.value = type
    }

    private val _selectedGame = MutableLiveData<String>()
    val selectedGame: LiveData<String> get() = _selectedGame

    fun setSelectedGame(game: String) {
        _selectedGame.value = game
    }

    fun clearTournament() {
        _selectedTournament.value = null
    }
}