package com.example.practicefortestapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicefortestapplication.databinding.ActivityMainBinding
import com.example.practicefortestapplication.p01event.ExerciseActivity
import com.example.practicefortestapplication.p02spinner.LostFoundActivity
import com.example.practicefortestapplication.p03bottomnav.BottomNavActivity
import com.example.practicefortestapplication.p04facilitydetail.FacilityNavActivity
import com.example.practicefortestapplication.p05drawer.DrawerMainActivity
import com.example.practicefortestapplication.p06recycler.NoticeActivity
import com.example.practicefortestapplication.p07recycleraction.NoticeActionActivity
import com.example.practicefortestapplication.p08memo.MemoActivity
import com.example.practicefortestapplication.p09contact.ContactActivity
import com.example.practicefortestapplication.p10book.BookActivity

/**
 * 런처 화면. 각 문제의 진입 Activity로 이동한다.
 * 문제별 구현은 com.example.practicefortestapplication.pNN... 패키지에 들어 있다.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnP01.setOnClickListener { open(ExerciseActivity::class.java) }
        binding.btnP02.setOnClickListener { open(LostFoundActivity::class.java) }
        binding.btnP03.setOnClickListener { open(BottomNavActivity::class.java) }
        binding.btnP04.setOnClickListener { open(FacilityNavActivity::class.java) }
        binding.btnP05.setOnClickListener { open(DrawerMainActivity::class.java) }
        binding.btnP06.setOnClickListener { open(NoticeActivity::class.java) }
        binding.btnP07.setOnClickListener { open(NoticeActionActivity::class.java) }
        binding.btnP08.setOnClickListener { open(MemoActivity::class.java) }
        binding.btnP09.setOnClickListener { open(ContactActivity::class.java) }
        binding.btnP10.setOnClickListener { open(BookActivity::class.java) }
    }

    private fun open(target: Class<*>) {
        startActivity(Intent(this, target))
    }
}
