package ru.sergeygap.gapstep.presentation.habit_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sergeygap.gapstep.domain.Habit

class HabitListViewModel : ViewModel() {

    private val _habits = MutableLiveData<List<Habit>>()
    val habits: LiveData<List<Habit>> = _habits

    init {
        val list = mutableListOf<Habit>()
        repeat(10) {
            list.add(
                Habit(
                    id = it,
                    name = "Item: $it",
                    count = it,
                    period = it + 1,
                )
            )
        }
        _habits.value = list
    }

    fun updateItemCount(direction: String) {
        when (direction) {
            "plus" -> {
                // Логика увеличения
            }
            "minus" -> {
                // Логика уменьшения
            }
        }
    }


}