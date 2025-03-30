package ru.sergeygap.gapstep.data

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import ru.sergeygap.gapstep.data.dto.HabitDto
import ru.sergeygap.gapstep.data.mapper.Mapper
import ru.sergeygap.gapstep.domain.entity.Habit
import ru.sergeygap.gapstep.domain.entity.HabitType
import ru.sergeygap.gapstep.domain.entity.SortType
import ru.sergeygap.gapstep.domain.repository.HabitRepository

object HabitRepositoryIml : HabitRepository {

    private var currentSearchQuery = ""
    private var currentSortType: SortType = SortType.NORMAL

    private val _listHabits = mutableListOf<HabitDto>().apply {
        add(
            HabitDto(
                id = 0,
                name = "Чтение",
                createdDate = Clock.System.now(),
                description = "Чтение книг по вечерам",
                priority = "Важная",
                type = HabitType.Useful,
                count = 0,
                period = 0,
                color = 0xFFFFFFAA.toInt()
            )
        )
        add(
            HabitDto(
                id = 1,
                name = "Прогулка",
                createdDate = Clock.System.now(),
                description = "Прогулка на свежем воздухе",
                priority = "Важная",
                type = HabitType.NotUseful,
                count = 0,
                period = 0,
                color = 0xFFAAFFAA.toInt()
            )
        )
        add(
            HabitDto(
                id = 2,
                name = "Медитация",
                createdDate = Clock.System.now(),
                description = "Медитация для расслабления",
                priority = "Важная",
                type = HabitType.Useful,
                count = 0,
                period = 0,
                color = 0xFFAAAAAA.toInt()
            )
        )
        add(
            HabitDto(
                id = 3,
                name = "Утренняя зарядка",
                createdDate = Clock.System.now(),
                description = "Зарядка для бодрости с утра",
                priority = "Важная",
                type = HabitType.NotUseful,
                count = 0,
                period = 0,
                color = 0xFFAAEEAA.toInt()
            )
        )
        add(
            HabitDto(
                id = 4,
                name = "Вечерняя прогулка",
                createdDate = Clock.System.now(),
                description = "Прогулка для релаксации вечером",
                priority = "Важная",
                type = HabitType.Useful,
                count = 0,
                period = 0,
                color = 0xFFEEFFAA.toInt()
            )
        )
        add(
            HabitDto(
                id = 5,
                name = "Изучение языков",
                createdDate = Clock.System.now(),
                description = "Занятия для изучения нового языка",
                priority = "Важная",
                type = HabitType.NotUseful,
                count = 0,
                period = 0,
                color = 0xFFAAFFEE.toInt()
            )
        )
        add(
            HabitDto(
                id = 6,
                name = "Плавание",
                createdDate = Clock.System.now(),
                description = "Плавание для поддержания формы",
                priority = "Важная",
                type = HabitType.Useful,
                count = 0,
                period = 0,
                color = 0xFFEEAAFF.toInt()
            )
        )
        add(
            HabitDto(
                id = 7,
                name = "Бег",
                createdDate = Clock.System.now(),
                description = "Бег для кардио-тренировки",
                priority = "Важная",
                type = HabitType.NotUseful,
                count = 0,
                period = 0,
                color = 0xFFAAFF00.toInt()
            )
        )
        add(
            HabitDto(
                id = 8,
                name = "Письмо дневника",
                createdDate = Clock.System.now(),
                description = "Запись мыслей и событий дня",
                priority = "Важная",
                type = HabitType.Useful,
                count = 0,
                period = 0,
                color = 0xFF00FFAA.toInt()
            )
        )
        add(
            HabitDto(
                id = 9,
                name = "Рисование",
                createdDate = Clock.System.now(),
                description = "Рисование для развития творчества",
                priority = "Важная",
                type = HabitType.NotUseful,
                count = 0,
                period = 0,
                color = 0xFFAA00FF.toInt()
            )
        )
        add(
            HabitDto(
                id = 10,
                name = "Игра на гитаре",
                createdDate = Clock.System.now(),
                description = "Игра на гитаре для релаксации",
                priority = "Важная",
                type = HabitType.Useful,
                count = 0,
                period = 0,
                color = 0xFF00AAFF.toInt()
            )
        )
        add(
            HabitDto(
                id = 11,
                name = "Йога",
                createdDate = Clock.System.now(),
                description = "Занятия йогой для гармонии души и тела",
                priority = "Важная",
                type = HabitType.NotUseful,
                count = 0,
                period = 0,
                color = 0xFFBB00FF.toInt()
            )
        )
        add(
            HabitDto(
                id = 12,
                name = "Кулинария1",
                createdDate = Instant.parse("2022-01-01T00:00:00Z"),
                description = "Тренировки в спортзале для силы",
                priority = "Важная",
                type = HabitType.Useful,
                count = 0,
                period = 0,
                color = 0xFFCC00FF.toInt()
            )
        )
        add(
            HabitDto(
                id = 13,
                name = "Кулинария2",
                createdDate = Instant.parse("2022-01-02T00:00:00Z"),
                description = "Приготовление новых блюд",
                priority = "Важная",
                type = HabitType.NotUseful,
                count = 0,
                period = 0,
                color = 0xFFDD00FF.toInt()
            )
        )
        add(
            HabitDto(
                id = 14,
                name = "Кулинария3",
                createdDate = Instant.parse("2022-01-03T00:00:00Z"),
                description = "Планирование задач на день",
                priority = "Важная",
                type = HabitType.Useful,
                count = 0,
                period = 0,
                color = 0xFFEE00FF.toInt()
            )
        )
        add(
            HabitDto(
                id = 15,
                name = "Садоводство",
                createdDate = Clock.System.now(),
                description = "Уход за растениями в саду",
                priority = "Важная",
                type = HabitType.NotUseful,
                count = 0,
                period = 0,
                color = 0xFF123456.toInt()
            )
        )
        add(
            HabitDto(
                id = 16,
                name = "Фотография",
                createdDate = Clock.System.now(),
                description = "Фотографирование природы",
                priority = "Важная",
                type = HabitType.Useful,
                count = 0,
                period = 0,
                color = 0xFF654321.toInt()
            )
        )
        add(
            HabitDto(
                id = 17,
                name = "Волонтёрство",
                createdDate = Clock.System.now(),
                description = "Помощь другим людям",
                priority = "Важная",
                type = HabitType.NotUseful,
                count = 0,
                period = 0,
                color = 0xFFABCDEF.toInt()
            )
        )
        add(
            HabitDto(
                id = 18,
                name = "Астрономия",
                createdDate = Clock.System.now(),
                description = "Наблюдение за звёздами",
                priority = "Важная",
                type = HabitType.Useful,
                count = 0,
                period = 0,
                color = 0xFFFEDCBA.toInt()
            )
        )
        add(
            HabitDto(
                id = 19,
                name = "Релаксация",
                createdDate = Instant.parse("2022-01-03T00:00:00Z"),
                description = "Расслабление после напряжённого дня",
                priority = "Важная",
                type = HabitType.NotUseful,
                count = 0,
                period = 0,
                color = 0xFF0F0F0F.toInt()
            )
        )
    }


    override fun getHabitById(id: Int): Habit {
        return _listHabits.find { it.id == id }?.let { Mapper.mapDtoToEntity(it) }
            ?: throw IllegalArgumentException("Habit with id $id not found")
    }

    override fun getListHabit(): List<Habit> = _listHabits.map { Mapper.mapDtoToEntity(it) }

    override fun addHabit(habit: Habit) {
        val newId = if (habit.id == -1) {
            (_listHabits.maxByOrNull { it.id }?.id ?: 0) + 1
        } else {
            habit.id
        }
        _listHabits.add(Mapper.mapEntityToDto(habit.copy(id = newId)))
    }

    override fun updateHabit(habit: Habit) {
        val index = _listHabits.indexOfFirst { it.id == habit.id }
        if (index != -1) {
            _listHabits[index] = Mapper.mapEntityToDto(habit)
        } else {
            throw IllegalArgumentException("Habit with id ${habit.id} not found")
        }
    }

    override fun deleteHabit(habit: Habit) {
        _listHabits.removeIf { it.id == habit.id }
    }

    override fun increaseCountInHabit(habit: Habit) {
        val index = _listHabits.indexOfFirst { it.id == habit.id }
        if (index != -1) {
            _listHabits[index] = Mapper.mapEntityToDto(habit.copy(count = habit.count + 1))
        } else {
            throw IllegalArgumentException("Habit with id ${habit.id} not found")
        }
    }

    override fun getSearchListHabitUseCase(): List<Habit> {
        return if (currentSearchQuery.isBlank()) {
            emptyList()
        } else {
            _listHabits
                .filter { it.name.contains(currentSearchQuery, ignoreCase = true) }
                .sortedWith { firstHabit, secondHabit ->
                    when (currentSortType) {
                        SortType.NEWEST -> secondHabit.createdDate.compareTo(firstHabit.createdDate)
                        SortType.OLDEST -> firstHabit.createdDate.compareTo(secondHabit.createdDate)
                        else -> 0
                    }
                }
                .map { Mapper.mapDtoToEntity(it) }
        }
    }

    override fun setSearchQueryUseCase(searchText: String, sortType: SortType) {
        currentSearchQuery = searchText
        currentSortType = sortType
    }
}
