package com.example.practicefortestapplication.p01event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicefortestapplication.databinding.P01ActivityExerciseBinding

/**
 * 문제 1. 운동 기록 화면: 이벤트 리스너 연결하기
 *
 * 요구사항
 *  1) cbWarmUp / cbCoolDown 체크 변화 → tvCheck 에 "완료 항목: 준비운동, 정리운동" (없으면 "완료 항목이 없습니다.")
 *  2) rgType 선택 변화 → tvType "운동 종류: 달리기"
 *  3) ratingIntensity 변화 → tvIntensity "운동 강도: 3점"
 *  4) etMemo 입력 변화 → tvMemoCount "12 / 50"
 *  5) btnSave 클릭: 메모 비면 Toast "메모를 입력하세요", 아니면 "저장되었습니다"
 *  6) btnSave 롱클릭: 전체 초기화 + Toast "초기화" (true 반환)
 *
 * xml(p01_activity_exercise.xml)은 완성되어 있다. 아래 리스너만 채우면 된다.
 */
class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: P01ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P01ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: 1번 문제 - 체크박스 2개에 setOnCheckedChangeListener 등록, 각각 updateCheckSummary() 호출

        // TODO: 1번 문제 - rgType.setOnCheckedChangeListener { group, checkedId -> ... } 로 tvType 갱신 (when(checkedId))

        // TODO: 1번 문제 - ratingIntensity.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> ... } 로 tvIntensity 갱신 (rating.toInt())

        // TODO: 1번 문제 - etMemo.addTextChangedListener(TextWatcher) 로 tvMemoCount 갱신 ("길이 / 50")

        // TODO: 1번 문제 - btnSave.setOnClickListener : 메모 공백이면 Toast 후 return, 아니면 저장 Toast

        // TODO: 1번 문제 - btnSave.setOnLongClickListener : 모든 입력 초기화 + Toast, return@setOnLongClickListener true
    }

    /**
     * TODO: 1번 문제 - 체크박스 두 개의 현재 상태를 읽어 tvCheck 문구를 다시 만든다.
     *  - 둘 다 해제: "완료 항목이 없습니다."
     *  - 예) "완료 항목: 준비운동, 정리운동"
     */
    private fun updateCheckSummary() {
        // TODO: 1번 문제 - 구현
    }
}
