package mx.ids.playbit.ui.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.utils.setupHideKeyboardOnOutsideTouch


abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {


    private var _binding: T? = null
    protected val binding get() = _binding!!

    abstract fun getViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
        binding.root.setupHideKeyboardOnOutsideTouch(this)
    }


    protected fun observeHideKeyboardEvent(hideKeyboardEvent: LiveData<Unit>) {
        hideKeyboardEvent.observe(this) {
            hideSoftKeyboard()
        }
    }

    private fun hideSoftKeyboard() {
        currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus()
        }
    }
}