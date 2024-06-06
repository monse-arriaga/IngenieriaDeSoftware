package mx.ids.playbit.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.IntentCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.R
import mx.ids.playbit.databinding.ActivityMainBinding
import mx.ids.playbit.model.user.JwtResponse
import mx.ids.playbit.ui.base.BaseActivity
import mx.ids.playbit.viewmodel.ParticipantViewModel
import mx.ids.playbit.viewmodel.TournamentViewModel
import mx.ids.playbit.viewmodel.UserViewModel

/**
 * HomeActivity used to show the chip navigation and it creates the viewmodels needed
 * by contained fragments, handles chip navigation behavior
 * @author Leonardo Aguilar Rodríguez
 *  */
@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityMainBinding>() {

    val viewModel: UserViewModel by viewModels()
    val tournamentViewModel: TournamentViewModel by viewModels()
    val participantViewModel: ParticipantViewModel by viewModels()
    private lateinit var navController: NavController

    override fun getViewBinding(): mx.ids.playbit.databinding.ActivityMainBinding =
        mx.ids.playbit.databinding.ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loggedInUserResponse: JwtResponse? =
            IntentCompat.getParcelableExtra(intent, "loggedInUser", JwtResponse::class.java)

        loggedInUserResponse?.let {
            viewModel.setLoggedInUser(it)
        } ?: run {
            Toast.makeText(this, "Error: User data is null", Toast.LENGTH_LONG).show()
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeFragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupNavigation()
    }

    private fun setupNavigation() {
        binding.navBar.setOnItemSelectedListener { id ->
            when (id) {
                R.id.home -> {
                    navController.navigate(
                        R.id.homeFragment, null, NavOptions.Builder()
                            .setPopUpTo(
                                R.id.homenav,
                                true
                            ) // Assuming 'homenav' is the ID of your navigation graph
                            .build()
                    )
                }

                R.id.tournaments -> {
                    navController.navigate(
                        R.id.viewTournamentsFragment, null, NavOptions.Builder()
                            .setPopUpTo(
                                R.id.homenav,
                                true
                            ) // Assuming 'homenav' is the ID of your navigation graph
                            .build()
                    )
                }
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> binding.navBar.setItemSelected(R.id.home, true)
                R.id.viewTournamentsFragment -> binding.navBar.setItemSelected(
                    R.id.tournaments,
                    true
                )

            }
        }
    }


    fun hideBar(flag: Boolean) {
        if (flag) binding.navBar.visibility = View.GONE else binding.navBar.visibility =
            View.VISIBLE
    }

}