package mx.ids.playbit.ui.home.tournaments

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.R
import mx.ids.playbit.databinding.FragmentViewTournamentsBinding
import mx.ids.playbit.ui.base.BaseFragment
import mx.ids.playbit.ui.home.HomeActivity
import mx.ids.playbit.viewmodel.TournamentViewModel
import mx.ids.playbit.viewmodel.UserViewModel
import javax.inject.Inject

/**
 * TournamentFragment used to display tournaments in a recyclerview
 * @author Leonardo Aguilar Rodríguez
 *  */
@AndroidEntryPoint
class TournamentFragment :
    BaseFragment<FragmentViewTournamentsBinding>(FragmentViewTournamentsBinding::inflate) {

    private val viewModel: UserViewModel by activityViewModels()
    private val tournamentViewModel: TournamentViewModel by activityViewModels()

    @Inject
    lateinit var adapter: TournamentAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            binding.ivBack.callOnClick()
        }
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.black)


        setupRecyclerView()
        if (!tournamentViewModel.tournaments.hasActiveObservers()) {
            tournamentViewModel.tournaments.observe(viewLifecycleOwner) { tournaments ->
                adapter.updateTournaments(tournaments)
            }
        }

        binding.ivBack.setOnClickListener {
            handleOnBackPressed()
        }


        val activity = activity
        if (activity is HomeActivity) {
            activity.hideBar(true)
        }
    }

    private fun handleOnBackPressed() {
        findNavController().navigate(
            R.id.homeFragment, null, NavOptions.Builder()
                .setPopUpTo(R.id.homenav, true)
                .build()
        )
    }

    private fun setupRecyclerView() {

        adapter.onItemClick = { tournament ->
            tournamentViewModel.setSelectedTournament(listOf(tournament))
            tournamentViewModel.findTournamentsByName(tournament.name)
            findNavController().navigate(R.id.action_viewTournamentsFragment_to_tournamentDetailFragment)
        }
        binding.rvTournaments.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTournaments.adapter = adapter


    }

    override fun onDestroy() {
        super.onDestroy()
        val activity = activity
        if (activity is HomeActivity) {
            activity.hideBar(false)
        }
    }


}