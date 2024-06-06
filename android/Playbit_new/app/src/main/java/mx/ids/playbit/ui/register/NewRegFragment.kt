package mx.ids.playbit.ui.register

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.R
import mx.ids.playbit.databinding.FragmentRegisterNewBinding
import mx.ids.playbit.model.user.SignupRequest
import mx.ids.playbit.ui.base.BaseFragment
import mx.ids.playbit.utils.OperationState
import mx.ids.playbit.utils.OperationType
import mx.ids.playbit.viewmodel.UserViewModel
/**
 * NewRegFragment used to register new user
 * @author Leonardo Aguilar Rodríguez
 *  */
@AndroidEntryPoint
class NewRegFragment :
    BaseFragment<FragmentRegisterNewBinding>(FragmentRegisterNewBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

        binding.etPwd.setOnClickListener {
            viewModel.togglePasswordVisibility()
        }

        binding.etConfirmPwd.setOnClickListener {
            viewModel.togglePasswordVisibility()
        }

        binding.btnIngresar.setOnClickListener {
            it.findNavController().apply {
                navigate(R.id.action_newRegFragment_to_landingFragment)
                popBackStack(R.id.newRegFragment, true)
            }
        }

        binding.btnRegistrar.setOnClickListener {

            val uname = binding.etUser.text.toString().trim()
            val password = binding.etPwd.text.toString().trim()
            val pwdConfirmed = binding.etConfirmPwd.text.toString().trim()
            val email = binding.etMail.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty() && pwdConfirmed.isNotEmpty() && uname.isNotEmpty()) {
                if (password == pwdConfirmed) {
                    val signupRequest =
                        SignupRequest(uname, email, password, "", setOf("ROLE_USER"))
                    binding.loaderView.visibility = View.VISIBLE
                    binding.btnRegistrar.isEnabled = false
                    viewModel.createUser(signupRequest)
                }
            }
        }
    }

    private fun setupObservers() {

        viewModel.isPasswordVisible.observe(viewLifecycleOwner, Observer { isVisible ->
            if (isVisible) {
                binding.etPwd.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_eye_closed,
                    0
                )
                binding.etPwd.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.etConfirmPwd.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_eye_closed,
                    0
                )
                binding.etConfirmPwd.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            } else {
                binding.etPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0)
                binding.etPwd.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.etConfirmPwd.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_eye,
                    0
                )
                binding.etConfirmPwd.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            val textLength = binding.etPwd.text.length
            if (textLength > 0) {
                binding.etPwd.setSelection(textLength)
            }

            val textLengthConfirm = binding.etConfirmPwd.text.length
            if (textLengthConfirm > 0) {
                binding.etConfirmPwd.setSelection(textLengthConfirm)
            }
        })

        viewModel.operationState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is OperationState.Loading -> {
                    binding.loaderView.visibility = View.VISIBLE
                    binding.btnRegistrar.isEnabled = false
                }

                is OperationState.Success -> {
                    binding.loaderView.visibility = View.GONE
                    binding.btnRegistrar.isEnabled = true
                    if (state.operation == OperationType.CREATE) {
                        Toast.makeText(requireContext(), "Registro Exitoso", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is OperationState.Error -> {
                    binding.loaderView.visibility = View.GONE
                    binding.btnRegistrar.isEnabled = true
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }

                else -> {
                    binding.loaderView.visibility = View.GONE
                    binding.btnRegistrar.isEnabled = true
                }
            }
        }
    }
}
