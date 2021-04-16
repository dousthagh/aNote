package co.nikavtech.anote.screens.activities.Splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import co.nikavtech.anote.R
import co.nikavtech.anote.databinding.ActivitySplashBinding
import co.nikavtech.anote.screens.activities.LoginAndRegister.LoginRegisterActivity
import co.nikavtech.anote.screens.activities.Main.MainActivity
import co.nikavtech.anote.services.setting.FirstRunServices

class SplashActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var firstRunServices: FirstRunServices
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        if (firstRunServices.needToLogin()) {
            startActivity(Intent(applicationContext, LoginRegisterActivity::class.java))
        } else {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
        finish()

    }

    fun init() {
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash)

        firstRunServices = FirstRunServices()

    }
}