package ru.sergeygap.gapstep.presentation.habit_list

import androidx.recyclerview.widget.RecyclerView
import ru.sergeygap.gapstep.databinding.ItemHabitBinding
import ru.sergeygap.gapstep.domain.Habit

class HabitListViewHolder(private val binding: ItemHabitBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(habit: Habit) {
        with(binding) {
            tvName.text = habit.name
            tvDescription.text = habit.description
            viewColor.setBackgroundColor(habit.color)
            tvCount.text = habit.count
            // TODO чипсы
        }
    }
}