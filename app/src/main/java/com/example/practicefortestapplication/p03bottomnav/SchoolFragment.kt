package com.example.practicefortestapplication.p03bottomnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practicefortestapplication.databinding.P03FragmentSchoolBinding

/**
 * 문제 3. 새로 추가할 "학교 소식" 화면. (클래스 자체는 제공)
 * 메뉴 / 네비게이션 그래프 / AppBarConfiguration 에 이 Fragment 를 연결하면 탭이 동작한다.
 */
class SchoolFragment : Fragment() {
    private var _binding: P03FragmentSchoolBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = P03FragmentSchoolBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNews.text = "10/27 학습법 특강 신청 마감 10/23"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
