package co.nikavtech.anote.screens.activities.Main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import co.nikavtech.anote.R
import co.nikavtech.anote.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initBottomNavigationView()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(getNavigationController(), drawerLayout)
    }

    fun getNavigationController(): NavController {
        return Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    fun init() {
        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        this.drawerLayout = binding.drawerLayout

        NavigationUI.setupActionBarWithNavController(this, getNavigationController(), drawerLayout)

        getNavigationController().addOnDestinationChangedListener { controller: NavController, destination: NavDestination, arguments: Bundle? ->
            run {
                if (destination.id == controller.graph.startDestination) {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                } else
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
        NavigationUI.setupWithNavController(binding.navView, getNavigationController())

    }

    fun initBottomNavigationView() {
        binding.bottomNavigationView.selectedItemId = R.id.homeFragment
        binding.bottomNavigationView.setupWithNavController(getNavigationController())
    }

}