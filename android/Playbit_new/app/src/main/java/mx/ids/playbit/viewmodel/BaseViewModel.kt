package mx.ids.playbit.viewmodel

import android.view.MotionEvent
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.ids.playbit.R

open class BaseViewModel : ViewModel() {
    private val _hideKeyboardEvent = MutableLiveData<Unit>()
    val hideKeyboardEvent: LiveData<Unit> = _hideKeyboardEvent

    private val _isPasswordVisible = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isPasswordVisible: LiveData<Boolean> get() = _isPasswordVisible

    fun togglePasswordVisibility() {
        _isPasswordVisible.value = _isPasswordVisible.value?.not()
    }

    private val _shouldChangeDrawable = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val _selectedItem = MutableLiveData<String>()
    val selectedItem: LiveData<String> get() = _selectedItem

    fun setSelectedItem(item: String) {
        _selectedItem.value = item
    }


    fun hideKeyboard() {
        _hideKeyboardEvent.value = Unit
    }

    fun isTouchOnDrawableEnd(editText: EditText, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            val drawableEnd = editText.compoundDrawables[2] ?: return false
            if (event.rawX >= (editText.right - drawableEnd.bounds.width())) {
                return true
            }
        }
        return false
    }

}