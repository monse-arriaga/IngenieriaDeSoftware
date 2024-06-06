package mx.ids.playbit.ui.home.createTournament

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
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.R
import mx.ids.playbit.databinding.FragmentCreateTournamentBinding
import mx.ids.playbit.model.tournament.Tournament
import mx.ids.playbit.ui.base.BaseFragment
import mx.ids.playbit.ui.home.HomeActivity
import mx.ids.playbit.utils.ERROR_SCREEN_TYPE
import mx.ids.playbit.utils.OperationState
import mx.ids.playbit.utils.OperationType
import mx.ids.playbit.utils.SUCCESS_SCREEN_TYPE
import mx.ids.playbit.utils.base64ToBitmap
import mx.ids.playbit.utils.handleImageUri
import mx.ids.playbit.utils.hideLoader
import mx.ids.playbit.utils.registerGalleryPicker
import mx.ids.playbit.utils.showAlert
import mx.ids.playbit.utils.showDatePickerDialog
import mx.ids.playbit.utils.showLoader
import mx.ids.playbit.utils.showTimePickerDialog
import mx.ids.playbit.viewmodel.TournamentViewModel
import mx.ids.playbit.viewmodel.UserViewModel

/**
 * CreateTournamentFragment used to create tournaments
 * @author Leonardo Aguilar Rodríguez
 *  */
@AndroidEntryPoint
class CreateTournamentFragment :
    BaseFragment<FragmentCreateTournamentBinding>(FragmentCreateTournamentBinding::inflate) {

    //viewmodels needed
    private val viewModel: UserViewModel by activityViewModels()
    private val tournamentViewModel: TournamentViewModel by activityViewModels()

    //To pick image from gallery but requirement changed to pick image from webview
    private val galleryPickerLauncher = registerGalleryPicker { uri: Uri ->
        handleImageUri(uri) { imageString ->
            tournamentViewModel.setSelectedImg(imageString)
            val bitmap = base64ToBitmap(imageString)
            binding.ivUploadImage.setImageBitmap(bitmap)
        }
    }
    //on destroy returns activity bar visibility
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
        setupSpinners()
        setupObservers()
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.black)

        setListeners()

        val activity = activity
        if (activity is HomeActivity) {
            activity.hideBar(true)
        }

        //for webview
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webViewClient = MyWebViewClient()
        binding.webview.addJavascriptInterface(WebAppInterface(tournamentViewModel), "Android")

    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSubirImagen.setOnClickListener {
            binding.webview.loadUrl("https://www.google.com/imghp")
            binding.webview.visibility = View.VISIBLE

        }

        binding.spTournamentGame.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedGame = parent.getItemAtPosition(position).toString()
                    tournamentViewModel.setSelectedGame(selectedGame)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

        binding.spTournamentType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedType = parent.getItemAtPosition(position).toString()
                    tournamentViewModel.setSelectedType(selectedType)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

        binding.btnSelectDate.setOnClickListener {
            showDatePickerDialog { date ->
                tournamentViewModel.setTournamentDate(date)
                binding.tvShowSelectedDate.text = date
            }
        }

        binding.btnSelectTime.setOnClickListener {
            showTimePickerDialog { time ->
                tournamentViewModel.setTournamentTime(time)
                binding.tvShowSelectedTime.text = time
            }
        }
        binding.btnCreateTournament.setOnClickListener {
            val tName = binding.etTournamentName.text.toString()
            val tPlayers = binding.etPlayersNum.text.toString().toIntOrNull() ?: 0
            val tDesc = binding.etDescripcion.text.toString()
            val tGame = tournamentViewModel.selectedGame.value
            val tType = tournamentViewModel.selectedType.value
            val tImg = tournamentViewModel.selectedImg.value
            val playersBT = binding.etPlayersBTeam.text.toString().toIntOrNull() ?: 0
            val prize = binding.etPrize.text.toString().toIntOrNull() ?: 0
            val date = tournamentViewModel.tournamentDate.value
            val time = tournamentViewModel.tournamentTime.value

            if (tName.isNotEmpty() && tDesc.isNotEmpty() && !tGame.isNullOrEmpty() && !tType.isNullOrEmpty()
                && !tImg.isNullOrEmpty() && !date.isNullOrEmpty() && !time.isNullOrEmpty() && tPlayers > 0
                && playersBT > 0 && prize > 0
            ) {
                val tournament = Tournament(
                    name = tName,
                    image = tImg,
                    players = tPlayers,
                    description = tDesc,
                    state = "",
                    tournamentType = tType,
                    tournamentGame = tGame,
                    playersBT = playersBT,
                    date = date,
                    prize = prize,
                    inPlayers = 0,
                    time = time
                )
                tournamentViewModel.createTournament(tournament)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Todos los campos son obligatorios",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }

    private fun setupSpinners() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dropdown_tournament_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spTournamentType.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dropdown_games,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spTournamentGame.adapter = adapter
        }

    }

    private fun setupObservers() {
        tournamentViewModel.operationState.observe(viewLifecycleOwner) { state ->
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
            OperationType.CREATE -> {
                showAlert(SUCCESS_SCREEN_TYPE, "¡Torneo creado con éxito!")
                tournamentViewModel.setOperationState(OperationState.Idle)
                tournamentViewModel.setSelectedImg("")
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
            OperationType.CREATE -> {
                showAlert(ERROR_SCREEN_TYPE, "¡Error al crear torneo!")
                tournamentViewModel.setOperationState(OperationState.Idle)
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


