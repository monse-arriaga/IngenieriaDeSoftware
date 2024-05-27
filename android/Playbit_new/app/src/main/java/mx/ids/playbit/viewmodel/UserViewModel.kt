package mx.ids.playbit.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.ids.playbit.model.LoginResponse
import mx.ids.playbit.model.MessageResponse
import mx.ids.playbit.model.User
import mx.ids.playbit.model.user.JwtResponse
import mx.ids.playbit.model.user.LoginRequest
import mx.ids.playbit.model.user.SignupRequest
import mx.ids.playbit.repository.UserRepository
import javax.inject.Inject
@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {

    private val _createUserResult = MutableLiveData<MessageResponse>()
    val createUserResult: LiveData<MessageResponse> = _createUserResult

    private val _loggedInUser = MutableLiveData<JwtResponse?>()
    val loggedInUser: LiveData<JwtResponse?> get() = _loggedInUser

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private val _enrollResult = MutableLiveData<MessageResponse>()
    val enrollResult: LiveData<MessageResponse> = _enrollResult

    private val _userTournaments = MutableLiveData<List<String>>()
    val userTournaments: LiveData<List<String>> = _userTournaments


    fun createUser(signupRequest: SignupRequest) {
        viewModelScope.launch {
            _createUserResult.value = repository.createUser(signupRequest)
        }
    }

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val response = repository.loginUser(loginRequest)
            _loggedInUser.value = response
            _loginResult.value = response != null
        }
    }

    fun enrollUser(username: String, tournamentName: String) {
        viewModelScope.launch {
            _enrollResult.value = repository.enrollUser(username, tournamentName)
        }
    }

    fun getUserTournaments(userId: Int) {
        viewModelScope.launch {
            _userTournaments.value = repository.getUserTournaments(userId)
        }
    }

    fun setLoggedInUser(user: JwtResponse) {
        _loggedInUser.value = user
    }

    fun hideKB() {
        hideKeyboard()
    }
}

