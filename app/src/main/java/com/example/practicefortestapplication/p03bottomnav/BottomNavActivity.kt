package com.example.practicefortestapplication.p03bottomnav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.practicefortestapplication.R
import com.example.practicefortestapplication.databinding.P03ActivityBottomNavBinding

/**
 * 문제 3. 하단 탭에 "학교 소식" 화면 추가
 *
 * 홈/시설/이용안내 3개 탭은 동작하도록 구성되어 있다.
 * 학교 소식 탭을 추가하려면 4곳을 같은 id(schoolFragment)로 맞춘다:
 *   1) SchoolFragment.kt (제공됨)
 *   2) res/menu/p03_bottom_nav_menu.xml 에 item 추가
 *   3) res/navigation/p03_mobile_navigation.xml 에 fragment 추가
 *   4) 아래 AppBarConfiguration 에 R.id.schoolFragment 추가 (최상위 → 뒤로가기 화살표 숨김)
 */
class BottomNavActivity : AppCompatActivity() {

    private lateinit var binding: P03ActivityBottomNavBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P03ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.facilityFragment, R.id.guideFragment
                // TODO: 3번 문제 - R.id.schoolFragment 를 최상위 집합에 추가
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
