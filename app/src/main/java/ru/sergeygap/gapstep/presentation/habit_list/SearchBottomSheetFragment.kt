package ru.sergeygap.gapstep.presentation.habit_list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.androidbroadcast.vbpd.viewBinding
import ru.sergeygap.gapstep.R
import ru.sergeygap.gapstep.databinding.FragmentSearchBottomSheetBinding
import ru.sergeygap.gapstep.domain.entity.SortType
import ru.sergeygap.gapstep.presentation.habit_list.recycler_view.HabitListAdapter


class SearchBottomSheetFragment : BottomSheetDialogFragment(R.layout.fragment_search_bottom_sheet) {

    private val binding: FragmentSearchBottomSheetBinding by viewBinding(
        FragmentSearchBottomSheetBinding::bind
    )
    private val viewModel: HabitListViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
    }

    private fun setupListeners() {

        binding.editTextSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.setSearchQuery(text.toString())
        }
        binding.chipGroupSort.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isEmpty()) return@setOnCheckedStateChangeListener
            when (checkedIds[0]) {
                R.id.chipNewest -> {
                    viewModel.sortType = SortType.NEWEST
                }

                R.id.chipOldest -> {
                    viewModel.sortType = SortType.OLDEST
                }

                else -> {
                    viewModel.sortType = SortType.NORMAL
                }
            }
            viewModel.updateSearchResult()
        }
    }

    private fun setupRecyclerView() {
        val adapter = HabitListAdapter(
            onItemClick = { habit ->
                val action = HabitListFragmentDirections
                    .actionHabitListFragmentToCreateHabitFragment(habit.id)
                findNavController().navigate(action)
            },
            onItemLongClick = { habit ->
                viewModel.increaseCountInHabit(habit)
            }
        )
        binding.rvSearch.adapter = adapter
        viewModel.habitsSearch.observe(viewLifecycleOwner) { habits ->
            (binding.rvSearch.adapter as? HabitListAdapter)?.submitList(habits)
            binding.rvSearch.isVisible = habits.isNotEmpty()
        }
    }

    companion object {

        private const val SEARCH_BOTTOM_SHEET = "SearchBottomSheet"

        fun newInstance(childFragmentManager: FragmentManager) {
            SearchBottomSheetFragment().show(childFragmentManager, SEARCH_BOTTOM_SHEET)
        }
    }
}