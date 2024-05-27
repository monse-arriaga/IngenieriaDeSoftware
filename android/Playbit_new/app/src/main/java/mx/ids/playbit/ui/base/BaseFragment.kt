package mx.ids.playbit.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.utils.KeyboardUtils
import mx.ids.playbit.viewmodel.BaseViewModel
import mx.ids.playbit.viewmodel.UserViewModel

abstract class BaseFragment<T : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    private var _binding: T? = null

    protected val binding: T
        get() = _binding ?: throw IllegalStateException("ViewBinding is accessed after it has been cleared or before it is initialized.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate.invoke(inflater, container, false)
        setupUI(binding.root)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun observeHideKeyboardEvent(hideKeyboardEvent: LiveData<Unit>) {
        hideKeyboardEvent.observe(viewLifecycleOwner) {
            hideSoftKeyboard()
        }
    }

    private fun hideSoftKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        val windowToken = view?.rootView?.windowToken
        if (windowToken != null && imm != null) {
            imm.hideSoftInputFromWindow(windowToken, 0)
            view?.clearFocus()
        }
    }

    private fun setupUI(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideSoftKeyboard()
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }
}
