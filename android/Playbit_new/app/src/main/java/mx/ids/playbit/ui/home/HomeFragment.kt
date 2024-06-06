package mx.ids.playbit.ui.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.R
import mx.ids.playbit.databinding.CardTournamentBinding
import mx.ids.playbit.databinding.FragmentHomeNewBinding
import mx.ids.playbit.model.tournament.Tournament
import mx.ids.playbit.model.tournament.TournamentItem
import mx.ids.playbit.model.user.Participant
import mx.ids.playbit.ui.base.BaseFragment
import mx.ids.playbit.utils.OperationState
import mx.ids.playbit.utils.OperationType
import mx.ids.playbit.utils.ui.CustomHorizontalCardView
import mx.ids.playbit.utils.ui.CustomVerticalCardView
import mx.ids.playbit.viewmodel.ParticipantViewModel
import mx.ids.playbit.viewmodel.TournamentViewModel
import mx.ids.playbit.viewmodel.UserViewModel
/**
 * HomeFragment used to display main info of tournament platform (tournaments, participants, games, user info)
 * @author Leonardo Aguilar Rodríguez
 *  */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeNewBinding>(FragmentHomeNewBinding::inflate) {

    private val viewModel: UserViewModel by activityViewModels()
    private val tournamentViewModel: TournamentViewModel by activityViewModels()
    private val participantViewModel: ParticipantViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tournamentViewModel.getAllTournaments()
        participantViewModel.findAll()
        setupTournaments()
        setupObservers()
        setupParticipants()


        binding.tvShowTorneos.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_viewTournamentsFragment)
        }

        viewModel.loggedInUser.observe(viewLifecycleOwner) { user ->
            binding.tvUname.text = "Hola, ${user?.username}"
        }
        binding.createTCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_createTournamentFragment)
        }

        binding.shapeableImageView.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        viewModel.findUserById(viewModel.loggedInUser.value?.id?.toInt() ?: 0)


        val cardViewId = binding.createTCard.id
        val newItem = TournamentItem(R.drawable.ic_plus, "Crear torneo")
        updateSingleCard(cardViewId, newItem, true)

        var gameOne = binding.cardGameOne.id
        var gameOneRes = TournamentItem(R.drawable.ic_chess, "Ajedrez")
        updateSingleCard(gameOne, gameOneRes, false)
        gameOne = binding.cardGameTwo.id
        gameOneRes = TournamentItem(R.drawable.ic_hockey, "Hockey")
        updateSingleCard(gameOne, gameOneRes, false)
        gameOne = binding.cardGameThree.id
        gameOneRes = TournamentItem(R.drawable.ic_pingpong, "Ping Pong")
        updateSingleCard(gameOne, gameOneRes, false)
        gameOne = binding.cardGameFour.id
        gameOneRes = TournamentItem(R.drawable.ic_football, "Fútbol")
        updateSingleCard(gameOne, gameOneRes, false)

    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.home_background)
    }

    private fun updateSingleCard(viewId: Int, newItem: TournamentItem, vertical: Boolean) {
        val cardView = when (vertical) {
            true -> binding.root.findViewById<CustomVerticalCardView>(viewId)
            false -> binding.root.findViewById<CustomHorizontalCardView>(viewId)
        }
        when (cardView) {
            is CustomVerticalCardView -> cardView.apply {
                setImageResource(newItem.imageResId)
                setText(newItem.text)
            }

            is CustomHorizontalCardView -> cardView.apply {
                setImageResource(newItem.imageResId)
                setText(newItem.text)
            }

            else -> throw IllegalArgumentException("Unsupported view type")
        }
    }

    private fun setupObservers() {
        tournamentViewModel.operationState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is OperationState.Loading -> {

                }

                is OperationState.Success -> {
                    handleSuccess(state.operation)

                }

                is OperationState.Error -> {
                    handleError(state.message, state.operation)

                }

                OperationState.Idle -> {}
                else -> {}
            }
        }
    }

    private fun handleSuccess(operation: OperationType) {
        when (operation) {
            OperationType.GETALL -> {
            }

            else -> {}

        }
    }

    private fun handleError(message: String, operation: OperationType) {
        when (operation) {
            OperationType.GETALL -> {
                binding.tournamentsContainer.visibility = View.GONE
            }

            else -> {}
        }
    }

    private fun setupParticipants() {

        participantViewModel.randomParticipants.observe(viewLifecycleOwner) { randomParticipants ->
            if (randomParticipants != null) {
                populateParticipants(randomParticipants)
            } else {
                binding.participantsContainer.visibility = View.GONE
            }

        }
    }

    private fun setupTournaments() {

        tournamentViewModel.randomTournaments.observe(viewLifecycleOwner) { randomTournaments ->
            if (!randomTournaments.isNullOrEmpty()) {
                binding.tournamentsContainer.visibility = View.VISIBLE
                populateHorizontalScrollView(randomTournaments)
            } else {
                binding.tournamentsContainer.visibility = View.GONE
            }
        }
    }

    private fun populateHorizontalScrollView(tournaments: List<Tournament>) {
        binding.linearLayout.removeAllViews()
        tournaments.forEach { tournament ->
            val cardView =
                CardTournamentBinding.inflate(layoutInflater, binding.linearLayout, false)
            val layoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.card_width),
                resources.getDimensionPixelSize(R.dimen.card_height)
            )
            layoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.card_margin_start)
            cardView.root.layoutParams = layoutParams
            //val bitmap = base64ToBitmap(decompressImageString(tournament.image))
            cardView.tvCard.setText(tournament.name)
            Glide.with(this)
                .load(tournament.image)
                .into(cardView.ivCard)
            binding.linearLayout.addView(cardView.root)
        }
    }

    private fun populateParticipants(participants: List<Participant>) {
        binding.participantsContainer.visibility = View.VISIBLE
        binding.participantsLayout.removeAllViews()
        participants.forEach { participant ->
            val cardView =
                CardTournamentBinding.inflate(layoutInflater, binding.participantsLayout, false)
            val layoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.card_width),
                resources.getDimensionPixelSize(R.dimen.card_height)
            )
            layoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.card_margin_start)
            cardView.root.layoutParams = layoutParams
            cardView.tvCard.setText(participant.name)

            binding.participantsLayout.addView(cardView.root)
        }
    }


}