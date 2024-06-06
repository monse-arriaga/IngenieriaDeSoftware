package mx.ids.playbit.utils

/**
 * Class used to operation state and type, helpful in api requests
 * @author Leonardo Aguilar Rodríguez
 *  */
sealed class OperationState {
    data class Success(val operation: OperationType) : OperationState()
    data class Error(val message: String, val operation: OperationType) : OperationState()
    object Loading : OperationState()
    object Idle : OperationState()
}

enum class OperationType {
    CREATE,
    EDIT,
    DELETE,
    GETALL,
    GETBYNAME,
    LOGIN,
    ENROLL,
    DELETE_ENROLLMENT,
    FIND_BY_NAME,
    FIND_BY_ID,
    GET_TOURNAMENTS,
    SUBSCRIBE

}