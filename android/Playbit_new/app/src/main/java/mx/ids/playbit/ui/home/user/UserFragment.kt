package mx.ids.playbit.ui.home.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.R
import mx.ids.playbit.databinding.FragmentProfileBinding
import mx.ids.playbit.model.User
import mx.ids.playbit.ui.base.BaseFragment
import mx.ids.playbit.ui.home.HomeActivity
import mx.ids.playbit.utils.ERROR_SCREEN_TYPE
import mx.ids.playbit.utils.OperationState
import mx.ids.playbit.utils.OperationType
import mx.ids.playbit.utils.SUCCESS_SCREEN_TYPE
import mx.ids.playbit.utils.hideLoader
import mx.ids.playbit.utils.showAlert
import mx.ids.playbit.utils.showDatePickerDialog
import mx.ids.playbit.utils.showLoader
import mx.ids.playbit.viewmodel.UserViewModel

/**
 * UserFragment used to display user profile and edit data if needed
 * @author Leonardo Aguilar Rodríguez
 *  */
@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::inflate
) {

    private val viewModel: UserViewModel by activityViewModels()

    //to know if its second time the edit/save button is pressed
    private var flag = false

    override fun onDestroy() {
        super.onDestroy()
        val activity = activity
        if (activity is HomeActivity) {
            activity.hideBar(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.black)


        binding.etUname.setText(viewModel.loggedInUser.value?.username.toString())
        binding.etEmail.setText(viewModel.findUserByIdResult.value?.get(0)?.email)
        binding.tvShowSelectedDate.setText(viewModel.findUserByIdResult.value?.get(0)?.bornDate)
        binding.etBio.setText(viewModel.findUserByIdResult.value?.get(0)?.bio)
        binding.btnBrnDate.isEnabled = false

        setListeners()

        val activity = activity
        if (activity is HomeActivity) {
            activity.hideBar(true)
        }

    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnBrnDate.setOnClickListener {
            showDatePickerDialog { date ->
                viewModel.setBorndate(date)
                binding.tvShowSelectedDate.text = date
            }
        }




        binding.btnEditUser.setOnClickListener {
            if (!flag) {
                binding.etUname.isFocusable = true
                binding.etUname.isFocusableInTouchMode = true
                binding.etUname.isCursorVisible = true
                binding.etEmail.isFocusable = true
                binding.etEmail.isFocusableInTouchMode = true
                binding.etEmail.isCursorVisible = true
                binding.etBio.isFocusable = true
                binding.etBio.isFocusableInTouchMode = true
                binding.etBio.isCursorVisible = true
                binding.btnBrnDate.isEnabled = true

                binding.tvEdit.setText("Guardar")
                Toast.makeText(requireContext(), "Actualiza tus datos", Toast.LENGTH_SHORT)
                    .show()
                flag = true
            } else {
                val user = User(
                    id = viewModel.findUserByIdResult.value?.get(0)?.id ?: 0,
                    name = binding.etUname.text.toString(),
                    email = binding.etEmail.text.toString(),
                    password = viewModel.findUserByIdResult.value?.get(0)?.password.toString(),
                    bornDate = viewModel.borndate.value.toString(),
                    bio = binding.etBio.text.toString()
                )

                if (user.name.isNotEmpty() && user.email.isNotEmpty() && user.bornDate.isNotEmpty()
                    && user.bio.isNotEmpty()
                ) {
                    viewModel.editUser(user)
                } else {
                    Toast.makeText(requireContext(), "Faltan datos de usuario", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }


    private fun setupObservers() {
        viewModel.operationState.observe(viewLifecycleOwner) { state ->
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
    }

    private fun handleSuccess(operation: OperationType) {
        when (operation) {
            OperationType.EDIT -> {
                showAlert(SUCCESS_SCREEN_TYPE, "¡Perfil actualizado con éxito!")
                viewModel.setOperationState(OperationState.Idle)
                findNavController().popBackStack()
            }

            OperationType.FIND_BY_NAME -> {
                viewModel.setOperationState(OperationState.Idle)
            }

            else -> {}

        }
    }

    private fun handleError(message: String, operation: OperationType) {
        when (operation) {
            OperationType.EDIT -> {
                showAlert(ERROR_SCREEN_TYPE, "¡Ocurrió un error al editar perfil!")
                viewModel.setOperationState(OperationState.Idle)
            }

            OperationType.FIND_BY_NAME -> {
                showAlert(ERROR_SCREEN_TYPE, "¡Error al obtener datos de usuario!")
                viewModel.setOperationState(OperationState.Idle)
            }

            else -> Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }


}