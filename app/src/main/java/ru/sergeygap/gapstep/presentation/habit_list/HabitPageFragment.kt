package ru.sergeygap.gapstep.presentation.habit_list

import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import dev.androidbroadcast.vbpd.viewBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import ru.sergeygap.gapstep.R
import ru.sergeygap.gapstep.databinding.FragmentHabitPageBinding
import ru.sergeygap.gapstep.domain.entity.HabitType
import ru.sergeygap.gapstep.presentation.habit_list.recycler_view.HabitListAdapter

class HabitPageFragment : Fragment(R.layout.fragment_habit_page) {

    private val binding by viewBinding(FragmentHabitPageBinding::bind)
    private val viewModel: HabitListViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupSwipeActions()
        setupScrollListener()
    }

    private fun setupAdapter() {
        val type = arguments?.getString(ARG_HABIT_TYPE) ?: return
        val habitType = HabitType.from(type) ?: return

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
        binding.rvHabit.adapter = adapter
        viewModel.habits.observe(viewLifecycleOwner) { habits ->
            (binding.rvHabit.adapter as? HabitListAdapter)?.submitList(habits.filter { it.type == habitType })
        }
    }

    private fun setupSwipeActions() {
        val swipeCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val adapter = binding.rvHabit.adapter as? HabitListAdapter
                val position = viewHolder.bindingAdapterPosition
                val habitColor = adapter?.currentList?.getOrNull(position)?.color
                    ?: Color.argb(0, 0, 0, 0)

                val maxDx = viewHolder.itemView.width * 0.25f
                val newDx = when {
                    dX > maxDx -> maxDx
                    dX < -maxDx -> -maxDx
                    else -> dX
                }

                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeRightBackgroundColor(habitColor)
                    .addSwipeRightLabel(getString(R.string.delete))
                    .setSwipeRightLabelColor(Color.WHITE)
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    newDx,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                if (position == RecyclerView.NO_POSITION) return
                val adapter = binding.rvHabit.adapter as? HabitListAdapter
                val habit = adapter?.currentList?.getOrNull(position) ?: return

                when (direction) {
                    ItemTouchHelper.RIGHT -> viewModel.deleteHabit(habit)
                }
            }
        }

        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.rvHabit)
    }

    private fun setupScrollListener() {
        binding.rvHabit.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val fabHabit =
                    requireParentFragment().view?.findViewById<ExtendedFloatingActionButton>(R.id.fab_habit)
                        ?: return
                when {
                    dy > 0 && fabHabit.isExtended -> fabHabit.shrink()
                    dy < 0 && !fabHabit.isExtended -> fabHabit.extend()
                }
            }
        })
    }

    companion object {
        private const val ARG_HABIT_TYPE = "ARG_HABIT_TYPE"

        fun newInstance(type: HabitType): HabitPageFragment = HabitPageFragment().apply {
            arguments = bundleOf(ARG_HABIT_TYPE to type.type)
        }
    }
}
