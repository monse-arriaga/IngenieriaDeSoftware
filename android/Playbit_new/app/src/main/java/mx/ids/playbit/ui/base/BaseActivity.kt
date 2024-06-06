package mx.ids.playbit.ui.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import mx.ids.playbit.utils.setupHideKeyboardOnOutsideTouch

/**
 * Base Activity used to prevent boilerplate in child activities
 * @author Leonardo Aguilar Rodríguez
 *  */
abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    abstract fun getViewBinding(): T

    //sets the view provided in activity constructor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
        //hides keyboard when touch is outside edittext
        binding.root.setupHideKeyboardOnOutsideTouch(this)
    }


}