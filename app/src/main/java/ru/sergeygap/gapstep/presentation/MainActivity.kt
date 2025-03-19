package ru.sergeygap.gapstep.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dev.androidbroadcast.vbpd.viewBinding
import ru.sergeygap.gapstep.R
import ru.sergeygap.gapstep.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO SplashScreenApi
        super.onCreate(savedInstanceState)
        setupEdgeToEdge()
        setupDrawerLayout()
    }

    private fun setupDrawerLayout() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.navView, navController)

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_about -> {
                    navController.navigate(R.id.aboutAppFragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.nav_habits -> {
                    navController.navigate(R.id.habitListFragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }


                else -> {
                    NavigationUI.onNavDestinationSelected(menuItem, navController)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
            }
        }
    }

    private fun setupEdgeToEdge() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.fragmentContainerView) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
