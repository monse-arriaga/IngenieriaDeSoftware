package mx.ids.playbit.ui.landing

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import mx.ids.playbit.databinding.ActivityLandingBinding
import mx.ids.playbit.ui.base.BaseActivity

@AndroidEntryPoint
class LandingActivity : BaseActivity<ActivityLandingBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        splash.setKeepOnScreenCondition{ false
        }
    }

    override fun getViewBinding(): ActivityLandingBinding = ActivityLandingBinding.inflate(layoutInflater)


}
