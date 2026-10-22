/*
 * 문제 5 정답 — 드로어 + 옵션 메뉴.
 * 바텀 내비게이션과의 핵심 차이: AppBarConfiguration 2번째 인자에 drawerLayout.
 * (참고 답안: 빌드에 포함되지 않음)
 */
package com.example.practicefortestapplication.p05drawer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.practicefortestapplication.R
import com.example.practicefortestapplication.databinding.P05ActivityMainBinding

class DrawerMainActivity : AppCompatActivity() {

    private lateinit var binding: P05ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P05ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_p05)

        // 2번째 인자 drawerLayout → 최상위 화면에서 햄버거(≡) 아이콘
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.facilityFragment, R.id.guideFragment),
            binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_p05)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.p05_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "설정 화면 준비 중", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
