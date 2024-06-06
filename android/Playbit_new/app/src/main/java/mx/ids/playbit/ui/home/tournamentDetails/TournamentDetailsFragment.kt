package mx.ids.playbit.ui.home.tournamentDetails

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.R
import mx.ids.playbit.databinding.ActivityMainBinding
import mx.ids.playbit.databinding.FragmentCreateTournamentBinding
import mx.ids.playbit.databinding.FragmentTournamentDetailBinding
import mx.ids.playbit.model.User
import mx.ids.playbit.model.tournament.Tournament
import mx.ids.playbit.model.tournament.TournamentAdminRequest
import mx.ids.playbit.model.user.Participant
import mx.ids.playbit.ui.base.BaseFragment
import mx.ids.playbit.ui.home.HomeActivity
import mx.ids.playbit.utils.ERROR_SCREEN_TYPE
import mx.ids.playbit.utils.OperationState
import mx.ids.playbit.utils.OperationType
import mx.ids.playbit.utils.SUCCESS_SCREEN_TYPE
import mx.ids.playbit.utils.base64ToBitmap
import mx.ids.playbit.utils.compressImageString
import mx.ids.playbit.utils.formatToString
import mx.ids.playbit.utils.handleImageUri
import mx.ids.playbit.utils.hideLoader
import mx.ids.playbit.utils.registerGalleryPicker
import mx.ids.playbit.utils.showAlert
import mx.ids.playbit.utils.showDatePickerDialog
import mx.ids.playbit.utils.showDatePickerDialogInLocal
import mx.ids.playbit.utils.showLoader
import mx.ids.playbit.utils.showTimePickerDialog
import mx.ids.playbit.utils.showTimePickerDialogInLocal
import mx.ids.playbit.viewmodel.ParticipantViewModel
import mx.ids.playbit.viewmodel.TournamentViewModel
import mx.ids.playbit.viewmodel.UserViewModel

/**
 * TournamentDetailFragment used to show the tournament details and subscribe to it if needed,
 * creating a participant
 * @author Leonardo Aguilar Rodríguez
 *  */
@AndroidEntryPoint
class TournamentDetailsFragment :
    BaseFragment<FragmentTournamentDetailBinding>(FragmentTournamentDetailBinding::inflate) {

    //viewmodels needed (all of them are obtained from activity)
    private val viewModel: UserViewModel by activityViewModels()
    private val tournamentViewModel: TournamentViewModel by activityViewModels()
    private val participantViewModel: ParticipantViewModel by activityViewModels()

    //preserved for edit functionality
    private val galleryPickerLauncher = registerGalleryPicker { uri: Uri ->
        handleImageUri(uri) { imageString ->
            tournamentViewModel.setSelectedImg(imageString)
            val bitmap = base64ToBitmap(imageString)
            binding.ivUploadImage.setImageBitmap(bitmap)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val activity = activity
        if (activity is HomeActivity) {
            activity.hideBar(false)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setupSpinners()
        setupObservers()
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.black)

        setListeners()
        setTournamentDetail()

        val activity = activity
        if (activity is HomeActivity) {
            activity.hideBar(true)
        }

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webViewClient = MyWebViewClient()
        binding.webview.addJavascriptInterface(WebAppInterface(tournamentViewModel), "Android")

    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }


        binding.btnSubscribe.setOnClickListener {

            val name = viewModel.loggedInUser.value?.username.toString()
            val tournamentId = tournamentViewModel.selectedTournament.value?.get(0)?.name.toString()
            val participant = Participant(id = 0, name = name, tournamentId = tournamentId)
            participantViewModel.createParticipant(listOf(participant))

        }
    }

    private fun setTournamentDetail() {
        binding.etTournamentName.setText(tournamentViewModel.selectedTournament.value?.get(0)?.name)
        binding.etPlayersNum.setText(tournamentViewModel.selectedTournament.value?.get(0)?.players.toString())
        binding.etDescripcion.setText(tournamentViewModel.selectedTournament.value?.get(0)?.description)
        setupSpinners()
        binding.tvUploadImage.text = "Imagen de Torneo"
        Glide.with(this)
            .load(tournamentViewModel.selectedTournament.value?.get(0)?.image)
            .into(binding.ivUploadImage)
        binding.etPlayersBTeam.setText(tournamentViewModel.selectedTournament.value?.get(0)?.playersBT.toString())
        binding.etPrize.setText(tournamentViewModel.selectedTournament.value?.get(0)?.prize.toString())
        binding.tvShowSelectedTime.setText(tournamentViewModel.selectedTournament.value?.get(0)?.time)
        binding.tvShowSelectedDate.setText(tournamentViewModel.selectedTournament.value?.get(0)?.date)

    }

    private fun setupSpinners() {
        val lockedMessage = tournamentViewModel.selectedTournament.value?.get(0)?.tournamentType
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf(lockedMessage)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spTournamentType.adapter = adapter
        binding.spTournamentType.isEnabled = false
        binding.spTournamentType.isClickable = false

        val game = tournamentViewModel.selectedTournament.value?.get(0)?.tournamentGame
        val adapterGame = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf(lockedMessage)
        )
        adapterGame.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spTournamentGame.adapter = adapter
        binding.spTournamentGame.isEnabled = false
        binding.spTournamentGame.isClickable = false

    }

    private fun setupObservers() {
        participantViewModel.operationState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is OperationState.Loading -> {
                    showLoader(binding.loaderView, binding.overlayView)
                }

                is OperationState.Success -> {
                    handleSuccess(state.operation)
                    hideLoader(binding.loaderView, binding.overlayView)
                }

                is OperationState.Error -> {
                    handleError(state.message, state.operation)
                    hideLoader(binding.loaderView, binding.overlayView)
                }

                OperationState.Idle -> {}
                else -> {
                    hideLoader(binding.loaderView, binding.overlayView)
                }
            }
        }

        tournamentViewModel.selectedImg.observe(viewLifecycleOwner) { imageUrl ->
            if (imageUrl.isNotEmpty()) {
                Glide.with(this)
                    .load(imageUrl)
                    .into(binding.ivUploadImage)
                binding.webview.visibility = View.GONE
            }

        }
    }

    private fun handleSuccess(operation: OperationType) {
        when (operation) {
            OperationType.SUBSCRIBE -> {
                showAlert(SUCCESS_SCREEN_TYPE, "¡Inscripción exitosa al torneo!")
                participantViewModel.setOperationState(OperationState.Idle)
                tournamentViewModel.setSelectedImg("")
                tournamentViewModel.clearTournament()
                findNavController().popBackStack()
            }

            OperationType.FIND_BY_NAME -> {
                viewModel.setOperationState(OperationState.Idle)
            }

            OperationType.EDIT -> Toast.makeText(
                requireContext(),
                "Torneo editado con éxito",
                Toast.LENGTH_SHORT
            ).show()

            OperationType.DELETE -> Toast.makeText(
                requireContext(),
                "Torneo eliminado con éxito",
                Toast.LENGTH_SHORT
            ).show()

            else -> {}

        }
    }

    private fun handleError(message: String, operation: OperationType) {
        when (operation) {
            OperationType.SUBSCRIBE -> {
                showAlert(ERROR_SCREEN_TYPE, "¡Error al inscribir torneo!")
                participantViewModel.setOperationState(OperationState.Idle)
                tournamentViewModel.setSelectedImg("")
            }

            OperationType.FIND_BY_NAME -> {
                showAlert(ERROR_SCREEN_TYPE, "¡Error al obtener datos de usuario!")
                viewModel.setOperationState(OperationState.Idle)
            }

            OperationType.EDIT -> Toast.makeText(requireContext(), message, Toast.LENGTH_LONG)
                .show()

            OperationType.DELETE -> Toast.makeText(requireContext(), message, Toast.LENGTH_LONG)
                .show()

            else -> {}
        }
    }


    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            injectImageClickListener()
        }
    }

    private fun injectImageClickListener() {
        binding.webview.evaluateJavascript(
            """
                (function() {
                    var images = document.getElementsByTagName('img');
                    for (var i = 0; i < images.length; i++) {
                        images[i].onclick = function() {
                            var imgUrl = this.src;
                            Android.onImageClick(imgUrl);
                        }
                    }
                })();
            """.trimIndent(), null
        )
    }

    inner class WebAppInterface(private val viewModel: TournamentViewModel) {

        @JavascriptInterface
        fun onImageClick(imageUrl: String) {
            viewModel.setSelectedImg(imageUrl)
        }
    }
}


