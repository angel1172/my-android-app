package com.example.myapplication.data.repository

import com.example.myapplication.R
import com.example.myapplication.data.dto.AppDto
import com.example.myapplication.data.mapper.AppMapper
import com.example.myapplication.domain.model.AppDomain
import kotlinx.coroutines.delay
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val mapper: AppMapper
) : AppsRepository {

    override suspend fun getApps(): List<AppDomain> {
        delay(300) // имитация сети
        val dtos = listOf(
            AppDto(R.drawable.ic_calendar, "Календарь", "Планируйте дела", "Продуктивность"),
            AppDto(R.drawable.ic_weather, "Погода", "Прогноз на неделю", "Утилиты"),
            AppDto(R.drawable.ic_notes, "Заметки", "Сохраняйте идеи", "Продуктивность"),
            AppDto(R.drawable.ic_calculator, "Калькулятор", "Быстрые расчёты", "Утилиты"),
            AppDto(R.drawable.ic_timer, "Таймер", "Контроль времени", "Продуктивность"),
            AppDto(R.drawable.ic_alarm, "Будильник", "Не проспите", "Утилиты"),
            AppDto(R.drawable.ic_converter, "Конвертер", "Единицы измерения", "Утилиты"),
            AppDto(R.drawable.ic_notebook, "Блокнот", "Текстовые заметки", "Продуктивность"),
            AppDto(R.drawable.ic_clock, "Часы", "Точное время", "Утилиты")
        )
        return dtos.map { mapper.toDomain(it) }
    }
}
