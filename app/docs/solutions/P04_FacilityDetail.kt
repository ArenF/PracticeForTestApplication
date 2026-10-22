/*
 * 문제 4 정답 — 시설 목록 → 상세로 facilityId 전달.
 * graph 의 action(action_facilityList_to_facilityDetail)은 이미 있다.
 * (참고 답안: 빌드에 포함되지 않음)
 */
package com.example.practicefortestapplication.p04facilitydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.practicefortestapplication.R
import com.example.practicefortestapplication.databinding.P04FragmentFacilityBinding
import com.example.practicefortestapplication.databinding.P04FragmentFacilityDetailBinding

// ───────── 목록: 클릭 시 bundleOf 로 facilityId 를 담아 navigate ─────────
class FacilityListFragment : Fragment() {
    private var _binding: P04FragmentFacilityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = P04FragmentFacilityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivDetail1.setOnClickListener { moveToDetail(view, "library") }
        binding.ivDetail2.setOnClickListener { moveToDetail(view, "cafeteria") }
    }

    private fun moveToDetail(view: View, facilityId: String) {
        val bundle = bundleOf("facilityId" to facilityId)
        view.findNavController()
            .navigate(R.id.action_facilityList_to_facilityDetail, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// ───────── 상세: arguments 로 받아 when 분기 (값 없어도 안전하게 ?:) ─────────
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

        val facilityId = arguments?.getString("facilityId") ?: "library"
        with(binding) {
            when (facilityId) {
                "library" -> {
                    tvFacilityName.text = "도서관"
                    tvLocation.text = "2호관 3층"
                    tvHours.text = "평일 09:00 ~ 21:00"
                    ivFacilityIcon.setImageResource(R.drawable.ic_library)
                }
                "cafeteria" -> {
                    tvFacilityName.text = "학생식당"
                    tvLocation.text = "1호관 지하 1층"
                    tvHours.text = "평일 11:00 ~ 14:00"
                    ivFacilityIcon.setImageResource(R.drawable.ic_cafeteria)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
