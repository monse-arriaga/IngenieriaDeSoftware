package mx.ids.playbit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.ids.playbit.R

/**
 * CustomCardViewmodel created to handle custom card view data
 * @author Leonardo Aguilar Rodríguez
 *  */
class CustomCardViewModel : BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Crear Torneo"
    }
    val text: LiveData<String> = _text

    private val _imageRes = MutableLiveData<Int>().apply {
        value = R.drawable.playbit
    }
    val imageRes: LiveData<Int> = _imageRes

    fun updateText(newText: String) {
        _text.value = newText
    }

    fun updateImage(newImageRes: Int) {
        _imageRes.value = newImageRes
    }
}