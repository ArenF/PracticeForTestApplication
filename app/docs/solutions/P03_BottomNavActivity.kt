/*
 * 문제 3 정답 — "학교 소식" 탭 추가. 4곳을 같은 id(schoolFragment)로 맞춘다.
 * (참고 답안: 빌드에 포함되지 않음)
 *
 * ── (1) res/menu/p03_bottom_nav_menu.xml 에 item 추가 ──────────────
 *   <item
 *       android:id="@+id/schoolFragment"
 *       android:icon="@drawable/ic_info"
 *       android:title="학교 소식" />
 *
 * ── (2) res/navigation/p03_mobile_navigation.xml 에 목적지 추가 ─────
 *   <fragment
 *       android:id="@+id/schoolFragment"
 *       android:name="com.example.practicefortestapplication.p03bottomnav.SchoolFragment"
 *       android:label="학교 소식" />
 *
 * ── (3) SchoolFragment.kt 는 이미 제공됨 ──────────────────────────
 * ── (4) 아래 AppBarConfiguration 에 R.id.schoolFragment 추가 ──────
 */
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
                R.id.homeFragment, R.id.facilityFragment, R.id.guideFragment,
                R.id.schoolFragment                       // ← 추가: 최상위 → Up 화살표 숨김
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
