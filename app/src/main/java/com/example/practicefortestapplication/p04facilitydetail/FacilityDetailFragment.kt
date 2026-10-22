package com.example.practicefortestapplication.p04facilitydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practicefortestapplication.databinding.P04FragmentFacilityDetailBinding

/**
 * 문제 4. 시설 상세 화면.
 *  - arguments 로 전달받은 "facilityId" 로 분기해 이름/위치/운영시간/아이콘을 채운다.
 *  - 값이 없어도 앱이 죽지 않아야 한다(기본값 처리).
 *
 *  library  → 도서관 / 2호관 3층 / 평일 09:00 ~ 21:00 / ic_library
 *  cafeteria→ 학생식당 / 1호관 지하 1층 / 평일 11:00 ~ 14:00 / ic_cafeteria
 */
class FacilityDetailFragment : Fragment() {
    private var _binding: P04FragmentFacilityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = P04FragmentFacilityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: 4번 문제 - arguments?.getString("facilityId") 로 값을 꺼내되 null 이면 기본값("library")
        // TODO: 4번 문제 - when(facilityId) 로 분기해 tvFacilityName/tvLocation/tvHours/ivFacilityIcon 설정
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
