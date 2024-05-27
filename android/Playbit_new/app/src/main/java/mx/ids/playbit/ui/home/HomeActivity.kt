package mx.ids.playbit.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.IntentCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.databinding.ActivityMainBinding
import mx.ids.playbit.model.LoginResponse
import mx.ids.playbit.model.user.JwtResponse
import mx.ids.playbit.ui.base.BaseActivity
import mx.ids.playbit.viewmodel.UserViewModel

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityMainBinding>() {

    val viewModel : UserViewModel by viewModels()

    override fun getViewBinding(): mx.ids.playbit.databinding.ActivityMainBinding = mx.ids.playbit.databinding.ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loggedInUserResponse: JwtResponse? = IntentCompat.getParcelableExtra(intent, "loggedInUser", JwtResponse::class.java)

        loggedInUserResponse?.let {
            viewModel.setLoggedInUser(it)
        }?: run {
            Toast.makeText(this, "Error: User data is null", Toast.LENGTH_LONG).show()
        }
    }

}