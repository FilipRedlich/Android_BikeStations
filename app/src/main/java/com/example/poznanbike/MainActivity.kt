package com.example.poznanbike

import android.animation.Animator
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.poznanbike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Animator.AnimatorListener {
    // view binding variable that allows for easy access to the Activities' Views
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private var firstStart: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { controller, destination, arguments ->
            if (!firstStart) {
                binding.fab.hide()
            }
            // Each time we change the navigation destination the menu will be invalidated
            invalidateOptionsMenu()
        })

        binding. fab .addOnHideAnimationListener(this)

        binding.fab.setOnClickListener {
            // placeholder for the FAB click event implementation
            val navHostFragment: NavHostFragment? =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
            if (navController.currentDestination?.id == R.id.listFragment) {
                val listFragment: ListFragment? =
                    navHostFragment?.childFragmentManager?.primaryNavigationFragment as ListFragment?

                listFragment?.clearList()
                listFragment?.populateListFromInternet()
            } else if (navController.currentDestination?.id == R.id.detailFragment) {
                val detailFragment: DetailFragment? =
                    navHostFragment?. childFragmentManager?.primaryNavigationFragment as DetailFragment?
                detailFragment?.saveToDatabase()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        firstStart = false;
    }


        override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val navDestination =
            navController.currentDestination ?: return super.onPrepareOptionsMenu(menu)
        return if (navDestination!!.id == R.id.detailFragment) {
            false // the menu disappears when the detailFragment is displayed
        } else {
            super.onPrepareOptionsMenu(menu)
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (navController.currentDestination?.id == R.id.listFragment) {
            val navHostFragment: NavHostFragment? =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
            // Perform actions for different menu options
            when (item.itemId) {
                R.id.get_all -> {

                }
                R.id.get_with_bikes -> {

                }
                R.id.get_with_racks -> {

                }
                R.id.clear -> {

                }
                else -> return true
            }
        }
        return  super.onOptionsItemSelected(item)
    }

    override fun onAnimationStart(pO: Animator?) {
    }
    override fun onAnimationEnd(pO: Animator?) {
        val currentDestination =
            findNavController(R.id.nav_host_fragment_content_main).currentDestination
        if (currentDestination?.id == R.id.listFragment) {
            binding.fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_update_24))
        } else if (currentDestination?.id == R.id.detailFragment) {
            binding.fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_save_24))
        }
        binding.fab.show()
    }
    override fun onAnimationCancel(pO: Animator?) {
    }
    override fun onAnimationRepeat(p0: Animator?) {
    }
}