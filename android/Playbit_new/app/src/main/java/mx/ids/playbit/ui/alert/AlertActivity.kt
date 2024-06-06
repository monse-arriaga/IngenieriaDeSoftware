package mx.ids.playbit.ui.alert

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.R
import mx.ids.playbit.databinding.ActivityAlertBinding
import mx.ids.playbit.ui.base.BaseActivity
import mx.ids.playbit.utils.ERROR_SCREEN_TYPE
import mx.ids.playbit.utils.EXTRA_MESSAGE
import mx.ids.playbit.utils.EXTRA_SCREEN_TYPE
import mx.ids.playbit.utils.SUCCESS_SCREEN_TYPE

/**
 * Generic Alert used to send success or error message screens to user
 * providing a better feedback
 * @author Leonardo Aguilar Rodríguez
 *  */
@AndroidEntryPoint
class AlertActivity : BaseActivity<ActivityAlertBinding>() {

    override fun getViewBinding(): ActivityAlertBinding =
        ActivityAlertBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenType = intent.getStringExtra(EXTRA_SCREEN_TYPE)
        val message = intent.getStringExtra(EXTRA_MESSAGE) ?: "Error en la operación"

        setupScreen(screenType, message)
        setupListeners()

    }

    private fun setupScreen(screenType: String?, message: String) {
        binding.tvMsg.text = message
        when (screenType) {
            SUCCESS_SCREEN_TYPE -> { //success screen
                binding.ivScreenType.setImageResource(R.drawable.ic_success)
            }

            ERROR_SCREEN_TYPE -> { //error screen
                binding.ivScreenType.setImageResource(R.drawable.ic_error)
            }

            else -> binding.ivScreenType.setImageResource(R.drawable.ic_warning) //warnings
        }
    }

    //listeners to handle close and back imageviews
    private fun setupListeners() {
        binding.ivClose.setOnClickListener {
            finish()
        }
        binding.btnExit.setOnClickListener {
            finish()
        }
    }

}