package ru.sergeygap.gapstep.presentation.habit_list.recycler_view

import androidx.recyclerview.widget.RecyclerView
import ru.sergeygap.gapstep.R
import ru.sergeygap.gapstep.databinding.ItemHabitBinding
import ru.sergeygap.gapstep.domain.entity.Habit

class HabitListViewHolder(
    private val binding: ItemHabitBinding,
    private val onItemClick: (Habit) -> Unit,
    private val onItemLongClick: (Habit) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(habit: Habit) {
        with(binding) {
            tvName.text = habit.name
            tvDescription.text = habit.description
            viewColor.setBackgroundColor(habit.color)
            priority.text = habit.priority
            type.text = habit.type.type
            tvCount.text = String.format(
                root.context.getString(R.string.times_and_period),
                habit.count,
                habit.period,
            )
            root.setOnClickListener {
                onItemClick(habit)
            }
            root.setOnLongClickListener {
                onItemLongClick(habit)
                true
            }
        }
    }
}
