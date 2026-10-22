/*
 * 문제 1 정답 — p01event/ExerciseActivity.kt 내용을 이 코드로 채우면 된다.
 * (이 파일은 app/docs 아래라 빌드에 포함되지 않는 참고 답안)
 */
package com.example.practicefortestapplication.p01event

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practicefortestapplication.R
import com.example.practicefortestapplication.databinding.P01ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: P01ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P01ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1) 체크박스 두 개가 같은 함수를 호출 (상태 변수 따로 두지 않고 isChecked 를 직접 읽는다)
        binding.cbWarmUp.setOnCheckedChangeListener { _, _ -> updateCheckSummary() }
        binding.cbCoolDown.setOnCheckedChangeListener { _, _ -> updateCheckSummary() }

        // 2) 라디오그룹: 어떤 버튼이 선택됐는지 checkedId 로 구분
        binding.rgType.setOnCheckedChangeListener { _, checkedId ->
            val type = when (checkedId) {
                R.id.rbRun -> "달리기"
                R.id.rbWalk -> "걷기"
                else -> "자전거"
            }
            binding.tvType.text = "운동 종류: $type"
        }

        // 3) 레이팅바: rating 은 Float → toInt()
        binding.ratingIntensity.setOnRatingBarChangeListener { _, rating, _ ->
            binding.tvIntensity.text = "운동 강도: ${rating.toInt()}점"
        }

        // 4) 글자 수: TextWatcher 는 메소드 3개를 모두 재정의
        binding.etMemo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvMemoCount.text = "${s?.length ?: 0} / 50"
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // 5) 저장: 검증 실패 시 return 으로 람다 빠져나오기
        binding.btnSave.setOnClickListener {
            if (binding.etMemo.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "메모를 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show()
        }

        // 6) 롱클릭: 초기화 후 true 반환 (isChecked=false 로 바꾸면 체크 리스너가 다시 호출돼 요약도 자동 갱신)
        binding.btnSave.setOnLongClickListener {
            binding.cbWarmUp.isChecked = false
            binding.cbCoolDown.isChecked = false
            binding.rgType.clearCheck()
            binding.ratingIntensity.rating = 0f
            binding.etMemo.setText("")
            Toast.makeText(this, "초기화", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
    }

    private fun updateCheckSummary() {
        val completedItems = StringBuffer()
        if (binding.cbWarmUp.isChecked) completedItems.append("준비운동")
        if (binding.cbCoolDown.isChecked) {
            if (completedItems.isNotEmpty()) completedItems.append(", ")
            completedItems.append("정리운동")
        }
        binding.tvCheck.text =
            if (completedItems.isEmpty()) "완료 항목이 없습니다."
            else "완료 항목: $completedItems"
    }
}
