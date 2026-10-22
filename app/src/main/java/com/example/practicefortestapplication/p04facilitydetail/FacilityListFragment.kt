package com.example.practicefortestapplication.p04facilitydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practicefortestapplication.databinding.P04FragmentFacilityBinding

/**
 * 문제 4. 시설 목록 화면.
 *  - ivDetail1 클릭 → 상세로 "library" 전달
 *  - ivDetail2 클릭 → 상세로 "cafeteria" 전달
 *
 * 이동은 nav action(action_facilityList_to_facilityDetail)과 bundleOf 를 사용한다.
 */
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

        // TODO: 4번 문제 - ivDetail1 클릭 시 moveToDetail(view, "library")
        // TODO: 4번 문제 - ivDetail2 클릭 시 moveToDetail(view, "cafeteria")
    }

    private fun moveToDetail(view: View, facilityId: String) {
        // TODO: 4번 문제 - bundleOf("facilityId" to facilityId) 로 번들을 만들고
        //  view.findNavController().navigate(R.id.action_facilityList_to_facilityDetail, bundle) 로 이동
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
