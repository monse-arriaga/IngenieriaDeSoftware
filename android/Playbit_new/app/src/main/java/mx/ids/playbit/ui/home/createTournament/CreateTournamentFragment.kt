package mx.ids.playbit.ui.home.createTournament

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.R
import mx.ids.playbit.databinding.FragmentCreateTournamentBinding
import mx.ids.playbit.ui.base.BaseFragment
import mx.ids.playbit.viewmodel.UserViewModel

@AndroidEntryPoint
class CreateTournamentFragment : BaseFragment<FragmentCreateTournamentBinding>(FragmentCreateTournamentBinding::inflate) {

    private val viewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinners()


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

    /*private fun setupObservers() {

        viewModel.isPasswordVisible.observe(viewLifecycleOwner, Observer { isVisible ->
            if (isVisible) {
                binding.etPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_closed, 0)
                binding.etPwd.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                binding.etPwd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0)
                binding.etPwd.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            // Asegurarse de mover el cursor dentro del rango válido del texto
            val textLength = binding.etPwd.text.length
            if (textLength > 0) {
                binding.etPwd.setSelection(textLength)
            }
        })


        viewModel.loginResult.observe(viewLifecycleOwner) { isSuccess ->

            binding.loaderView.visibility = View.GONE
            binding.btnIngresar.isEnabled = true
            if (isSuccess) {

                Toast.makeText(requireContext(), "Inicio Exitoso", Toast.LENGTH_LONG).show()
                // Handle login success, e.g., navigate to another activity
            } else {

                Toast.makeText(requireContext(), "Inicio Fallido", Toast.LENGTH_LONG).show()
                // Handle failure, e.g., show an error message
            }
        }

    }*/

}