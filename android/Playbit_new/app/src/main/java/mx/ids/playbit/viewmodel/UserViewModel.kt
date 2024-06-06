package mx.ids.playbit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.ids.playbit.model.MessageResponse
import mx.ids.playbit.model.User
import mx.ids.playbit.model.user.EnrollDeleteRequest
import mx.ids.playbit.model.user.JwtResponse
import mx.ids.playbit.model.user.LoginRequest
import mx.ids.playbit.model.user.SignupRequest
import mx.ids.playbit.repository.UserRepository
import mx.ids.playbit.utils.OperationState
import mx.ids.playbit.utils.OperationType
import javax.inject.Inject
/**
 * UserViewModel communication bridge between view, model and repository
 * @author Leonardo Aguilar Rodríguez
 *  */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {

    private val _borndate = MutableLiveData<String>()
    val borndate: LiveData<String> get() = _borndate

    private val _operationState = MutableLiveData<OperationState>()
    val operationState: LiveData<OperationState> get() = _operationState

    private val _createUserResult = MutableLiveData<MessageResponse>()
    val createUserResult: LiveData<MessageResponse> = _createUserResult

    private val _loggedInUser = MutableLiveData<JwtResponse?>()
    val loggedInUser: LiveData<JwtResponse?> get() = _loggedInUser

    private val _enrollResult = MutableLiveData<MessageResponse>()
    val enrollResult: LiveData<MessageResponse> = _enrollResult

    private val _userTournaments = MutableLiveData<List<String>>()
    val userTournaments: LiveData<List<String>> = _userTournaments

    private val _editUserResult = MutableLiveData<MessageResponse>()
    val editUserResult: LiveData<MessageResponse> = _editUserResult

    private val _deleteEnrollmentResult = MutableLiveData<MessageResponse>()
    val deleteEnrollmentResult: LiveData<MessageResponse> = _deleteEnrollmentResult

    private val _findUserByNameResult = MutableLiveData<List<User>>()
    val findUserByNameResult: LiveData<List<User>> = _findUserByNameResult

    private val _findUserByIdResult = MutableLiveData<List<User>>()
    val findUserByIdResult: LiveData<List<User>> = _findUserByIdResult

    fun setOperationState(state: OperationState) {
        _operationState.value = state
    }

    fun setBorndate(date: String) {
        _borndate.value = date
    }

    fun createUser(signupRequest: SignupRequest) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val response = repository.createUser(signupRequest)
                _createUserResult.value = response
                _operationState.value = OperationState.Success(OperationType.CREATE)
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.CREATE)
            }
        }
    }

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val response = repository.loginUser(loginRequest)
                _loggedInUser.value = response
                _operationState.value = OperationState.Success(OperationType.LOGIN)
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.LOGIN)
            }
        }
    }

    fun enrollUser(username: String, tournamentName: String) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val response = repository.enrollUser(username, tournamentName)
                _enrollResult.value = response
                _operationState.value = OperationState.Success(OperationType.ENROLL)
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.ENROLL)
            }
        }
    }

    fun getUserTournaments(userId: Int) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val tournaments = repository.getUserTournaments(userId)
                _userTournaments.value = tournaments
                _operationState.value = OperationState.Success(OperationType.GET_TOURNAMENTS)
            } catch (e: Exception) {
                _operationState.value = OperationState.Error(
                    e.message ?: "Unknown error",
                    OperationType.GET_TOURNAMENTS
                )
            }
        }
    }

    fun editUser(user: User) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val response = repository.editUser(user)
                _editUserResult.value = response
                _operationState.value = OperationState.Success(OperationType.EDIT)
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.EDIT)
            }
        }
    }

    fun deleteUserEnrollment(enrollDeleteRequest: EnrollDeleteRequest) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val response = repository.deleteUserEnrollment(enrollDeleteRequest)
                _deleteEnrollmentResult.value = response
                _operationState.value = OperationState.Success(OperationType.DELETE_ENROLLMENT)
            } catch (e: Exception) {
                _operationState.value = OperationState.Error(
                    e.message ?: "Unknown error",
                    OperationType.DELETE_ENROLLMENT
                )
            }
        }
    }

    fun findUserByName(username: String) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val users = repository.findUserByName(username)
                _findUserByNameResult.value = users
                _operationState.value = OperationState.Success(OperationType.FIND_BY_NAME)
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.FIND_BY_NAME)
            }
        }
    }

    fun findUserById(id: Int) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val users = repository.findUserById(id)
                _findUserByIdResult.value = users
                _operationState.value = OperationState.Success(OperationType.FIND_BY_ID)
            } catch (e: Exception) {
                _operationState.value =
                    OperationState.Error(e.message ?: "Unknown error", OperationType.FIND_BY_ID)
            }
        }
    }

    fun setLoggedInUser(user: JwtResponse) {
        _loggedInUser.value = user
    }

    fun hideKB() {
        hideKeyboard()
    }
}

