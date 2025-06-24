package com.hongtian.flowfree

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // 使用post确保视图完全初始化
        bottomNav.post {
            setupBottomNavigation(bottomNav)

            // 仅在第一次创建时初始化默认Fragment
            if (savedInstanceState == null) {
                replaceFragment(HomeFragment(), addToBackStack = false)
            }
        }
    }

    private fun setupBottomNavigation(bottomNav: BottomNavigationView) {
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fabu -> replaceFragment(FabuFragment())
                R.id.home -> replaceFragment(HomeFragment())
                R.id.search -> replaceFragment(SearchFragment())
                R.id.dashboard -> replaceFragment(DashboardFragment())
                R.id.notifications -> replaceFragment(NotificationsFragment())
            }
            true
        }

        // 设置默认选中项
        bottomNav.selectedItemId = R.id.home
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.fragment_container, fragment)
            .apply {
                if (addToBackStack) {
                    addToBackStack(fragment::class.java.simpleName)
                }
            }
            .commit()
    }

    override fun onBackPressed() {
        // 如果栈中有多个Fragment，先回退Fragment
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
