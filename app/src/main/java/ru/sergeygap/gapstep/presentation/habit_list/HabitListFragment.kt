package ru.sergeygap.gapstep.presentation.habit_list

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dev.androidbroadcast.vbpd.viewBinding
import ru.sergeygap.gapstep.R
import ru.sergeygap.gapstep.databinding.FragmentHabitListBinding
import ru.sergeygap.gapstep.domain.entity.HabitType

class HabitListFragment : Fragment(R.layout.fragment_habit_list) {

    private val binding: FragmentHabitListBinding by viewBinding(FragmentHabitListBinding::bind)
    private val viewModel: HabitListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPagerWithTabs()
        setupFAB()
    }

    private fun setupFAB() {
        binding.fabHabit.setOnClickListener {
            findNavController().navigate(R.id.action_habitListFragment_to_createHabitFragment)
        }
        if (viewModel.habits.value.isNullOrEmpty()) {
            binding.fabHabit.apply {
                text = getString(R.string.create_first_habit)
                icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.trending_up_24px)
                updateLayoutParams {
                    width = ViewGroup.LayoutParams.MATCH_PARENT
                }
            }
        }
    }

    private fun setupViewPagerWithTabs() {
        val listTypes = listOf(HabitType.Useful, HabitType.NotUseful)

        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return HabitPageFragment.newInstance(listTypes[position])
            }

            override fun getItemCount() = listTypes.size
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = listTypes[position].type
        }.attach()
    }
}
