package ru.sergeygap.gapstep.data.mapper

import ru.sergeygap.gapstep.data.dto.HabitDto
import ru.sergeygap.gapstep.domain.entity.Habit
import ru.sergeygap.gapstep.util.convertToInstant
import ru.sergeygap.gapstep.util.convertToString

object HabitMapper {

    fun mapDtoToEntity(dto: HabitDto) = Habit(
        id = dto.id,
        name = dto.name,
        createdDate = dto.createdDate.convertToString(),
        description = dto.description,
        priority = dto.priority,
        type = dto.type,
        count = dto.count,
        period = dto.period,
        color = dto.color,
    )

    fun mapEntityToDto(entity: Habit) = HabitDto(
        id = entity.id,
        name = entity.name,
        createdDate = entity.createdDate.convertToInstant(),
        description = entity.description,
        priority = entity.priority,
        type = entity.type,
        count = entity.count,
        period = entity.period,
        color = entity.color,
    )
}