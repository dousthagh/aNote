package co.nikavtech.anote.screens.activities.LoginAndRegister

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import co.nikavtech.anote.R
import co.nikavtech.anote.databinding.ActivityLoginRegisterBinding

class LoginRegisterActivity:AppCompatActivity() {
    private lateinit var binding: ActivityLoginRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_register)
    }
}