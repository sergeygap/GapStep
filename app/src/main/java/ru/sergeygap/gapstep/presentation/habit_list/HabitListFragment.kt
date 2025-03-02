package ru.sergeygap.gapstep.presentation.habit_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dev.androidbroadcast.vbpd.viewBinding
import ru.sergeygap.gapstep.R
import ru.sergeygap.gapstep.databinding.FragmentHabitListBinding

class HabitListFragment : Fragment(R.layout.fragment_habit_list) {

    private val binding: FragmentHabitListBinding by viewBinding(FragmentHabitListBinding::bind)
    private val viewModel: HabitListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setupScrollListener()
        setupSwipeActions()
    }

    private fun setUpAdapter() {
        HabitListAdapter().apply {
            binding.rvHabit.adapter = this
            submitList(viewModel.habits.value)
        }
    }

    private fun setupSwipeActions() {
        val swipeCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (position == RecyclerView.NO_POSITION) return
                val adapter = binding.rvHabit.adapter as? HabitListAdapter
                val item = adapter?.currentList?.getOrNull(position)

                when (direction) {
                    ItemTouchHelper.LEFT -> viewModel.updateItemCount("minus")

                    ItemTouchHelper.RIGHT -> viewModel.updateItemCount("plus")

                }
                adapter?.notifyItemChanged(position)
            }
        }

        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.rvHabit)
    }


    private fun setupScrollListener() {
        binding.rvHabit.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                when {
                    dy > 0 && binding.fabHabit.isExtended -> binding.fabHabit.shrink()
                    dy < 0 && !binding.fabHabit.isExtended -> binding.fabHabit.extend()
                }
            }
        })
    }
}