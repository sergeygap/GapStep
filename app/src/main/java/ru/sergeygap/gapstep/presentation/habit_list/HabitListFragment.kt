package ru.sergeygap.gapstep.presentation.habit_list

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.leinardi.android.speeddial.SpeedDialActionItem
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
        setupSpeedDial()
        binding.fabHabit.setOnClickListener {
            if (binding.speedDial.isOpen) {
                binding.speedDial.close()
            } else {
                binding.speedDial.open()
            }
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

    private fun setupSpeedDial() {

        binding.speedDial.addActionItem(
            SpeedDialActionItem.Builder(R.id.fab_create_habit, R.drawable.add_24px)
                .setLabel(getString(R.string.create_habit))
                .create()
        )
        binding.speedDial.addActionItem(
            SpeedDialActionItem.Builder(R.id.fab_search, R.drawable.search_24px)
                .setLabel(getString(R.string.search))
                .create()
        )

        binding.speedDial.setOnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.fab_create_habit -> {
                    findNavController().navigate(R.id.action_habitListFragment_to_createHabitFragment)
                    binding.speedDial.close()
                    true
                }

                R.id.fab_search -> {

                    binding.speedDial.close()
                    true
                }

                else -> false
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
