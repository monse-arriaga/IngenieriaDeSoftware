package mx.ids.playbit.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


fun View.setupHideKeyboardOnOutsideTouch(context: Context) {
    // If the view is a ViewGroup, set up touch listeners for its children
    if (this is ViewGroup) {
        for (i in 0 until childCount) {
            getChildAt(i).setupHideKeyboardOnOutsideTouch(context)
        }
    }

    // Unless it's an EditText, set a touch listener to hide the keyboard
    if (this !is EditText) {
        setOnTouchListener { _, _ ->
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val currentFocusedView = (context as? Activity)?.currentFocus
            currentFocusedView?.let {
                imm.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                it.clearFocus()
            }
            false
        }
    }
}