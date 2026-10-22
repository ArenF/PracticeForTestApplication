package com.example.practicefortestapplication.p05drawer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.practicefortestapplication.R
import com.example.practicefortestapplication.databinding.P05ActivityMainBinding

/**
 * 문제 5. 드로어 메뉴 + 툴바 뒤로가기 + 옵션 메뉴
 *
 * 요구사항
 *  1) 햄버거(≡) → 드로어 열림, 홈/시설/이용안내 이동
 *  2) 현재 화면 메뉴 항목만 선택 표시 (메뉴 group checkableBehavior="single" 로 처리됨)
 *  3) 상세 화면에서는 화살표 (이 예시는 최상위 3개만 있어 항상 햄버거)
 *  4) 옵션 메뉴 "설정" → Toast "설정 화면 준비 중"
 *
 * 바텀 내비게이션(문제 3)과의 핵심 차이: AppBarConfiguration 의 2번째 인자로 drawerLayout 을 준다.
 */
class DrawerMainActivity : AppCompatActivity() {

    private lateinit var binding: P05ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    // FragmentContainerView 로 띄운 NavHost 는 FragmentManager 에서 꺼내야 안전하다.
    private val navController: NavController
        get() = (supportFragmentManager
            .findFragmentById(R.id.nav_host_p05) as NavHostFragment)
            .navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P05ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // TODO: 5번 문제 - AppBarConfiguration 의 2번째 인자로 binding.drawerLayout 을 전달해
        //  최상위 화면에서 햄버거(≡) 아이콘이 나오도록 한다.
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.facilityFragment, R.id.guideFragment)
            /* , binding.drawerLayout */
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // TODO: 5번 문제 - onCreateOptionsMenu 를 재정의해 R.menu.p05_main 을 inflate 한다.

    // TODO: 5번 문제 - onOptionsItemSelected 를 재정의해 R.id.action_settings 클릭 시
    //  Toast "설정 화면 준비 중" 을 띄우고 true 를 반환한다.
}
