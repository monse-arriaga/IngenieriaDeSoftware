package mx.ids.playbit.ui.landing

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.R
import mx.ids.playbit.databinding.FragmentLoginNewBinding
import mx.ids.playbit.model.user.LoginRequest
import mx.ids.playbit.ui.base.BaseFragment
import mx.ids.playbit.ui.home.HomeActivity
import mx.ids.playbit.utils.OperationState
import mx.ids.playbit.utils.OperationType
import mx.ids.playbit.viewmodel.UserViewModel
/**
 * LandingFragment used to login or handle register button behavior
 * if register button is pressed navigates to NewRegFragment
 * @author Leonardo Aguilar Rodríguez
 *  */
@AndroidEntryPoint
class LandingFragment : BaseFragment<FragmentLoginNewBinding>(FragmentLoginNewBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        binding.etPwd.setOnTouchListener { v, event ->
            if (viewModel.isTouchOnDrawableEnd(binding.etPwd, event)) {
                viewModel.togglePasswordVisibility()
                true
            } else {
                false
            }
        }

        binding.btnRegistrar.setOnClickListener {
            it.findNavController().navigate(R.id.action_landingFragment_to_newRegFragment)
        }

        binding.btnIngresar.setOnClickListener {
            val uname = binding.etUname.text.toString().trim()
            val password = binding.etPwd.text.toString().trim()
            if (uname.isNotEmpty() && password.isNotEmpty()) {
                binding.loaderView.visibility = View.VISIBLE
                binding.btnIngresar.isEnabled = false
                val loginRequest = LoginRequest(uname, password)
                viewModel.loginUser(loginRequest)
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
            } else {
                binding.etPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0)
                binding.etPwd.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            val textLength = binding.etPwd.text.length
            if (textLength > 0) {
                binding.etPwd.setSelection(textLength)
            }
        })


        viewModel.operationState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is OperationState.Loading -> {
                    binding.loaderView.visibility = View.VISIBLE
                    binding.btnIngresar.isEnabled = false
                }

                is OperationState.Success -> {
                    binding.loaderView.visibility = View.GONE
                    binding.btnIngresar.isEnabled = true
                    if (state.operation == OperationType.LOGIN) {
                        val intent = Intent(requireContext(), HomeActivity::class.java)
                        viewModel.loggedInUser.value?.let { user ->
                            intent.putExtra("loggedInUser", user)
                        }
                        startActivity(intent)
                        requireActivity().finish()
                    }
                }

                is OperationState.Error -> {
                    binding.loaderView.visibility = View.GONE
                    binding.btnIngresar.isEnabled = true
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }

                else -> {
                    binding.loaderView.visibility = View.GONE
                    binding.btnIngresar.isEnabled = true
                }
            }
        }

    }

}