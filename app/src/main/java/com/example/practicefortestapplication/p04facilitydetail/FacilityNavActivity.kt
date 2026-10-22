package com.example.practicefortestapplication.p04facilitydetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.practicefortestapplication.R
import com.example.practicefortestapplication.databinding.P04ActivityFacilityNavBinding

/**
 * 문제 4. 시설 목록 → 상세 화면 데이터 전달 (Navigation 호스트)
 *
 * facilityListFragment 만 최상위이고, facilityDetailFragment 는 최상위가 아니므로
 * 툴바에 뒤로가기 화살표가 생긴다.
 */
class FacilityNavActivity : AppCompatActivity() {

    private lateinit var binding: P04ActivityFacilityNavBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    // FragmentContainerView 로 띄운 NavHost 는 FragmentManager 에서 꺼내야 안전하다.
    private val navController: NavController
        get() = (supportFragmentManager
            .findFragmentById(R.id.nav_host_p04) as NavHostFragment)
            .navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P04ActivityFacilityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.facilityListFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
