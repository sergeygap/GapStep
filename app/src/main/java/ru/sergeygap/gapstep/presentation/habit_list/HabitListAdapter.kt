package ru.sergeygap.gapstep.presentation.habit_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.sergeygap.gapstep.databinding.ItemHabitBinding
import ru.sergeygap.gapstep.domain.entity.Habit

class HabitListAdapter : ListAdapter<Habit, HabitListViewHolder>(HabitListDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = HabitListViewHolder(
        ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: HabitListViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)
    }
}