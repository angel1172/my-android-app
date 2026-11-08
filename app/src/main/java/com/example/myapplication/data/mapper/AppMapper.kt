package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.AppDto
import com.example.myapplication.domain.model.AppDomain

class AppMapper @Inject constructor() {
    fun toDomain(dto: AppDto): AppDomain =
        AppDomain(
            iconId = dto.iconResId,
            name = dto.name,
            description = dto.description,
            category = dto.category
        )
}

