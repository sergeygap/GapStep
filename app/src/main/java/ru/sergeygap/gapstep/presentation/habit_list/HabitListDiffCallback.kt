package ru.sergeygap.gapstep.presentation.habit_list

import androidx.recyclerview.widget.DiffUtil
import ru.sergeygap.gapstep.domain.entity.Habit

class HabitListDiffCallback : DiffUtil.ItemCallback<Habit>() {
    override fun areItemsTheSame(
        oldItem: Habit,
        newItem: Habit
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Habit,
        newItem: Habit
    ) = oldItem == newItem
}