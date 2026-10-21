package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val showcaseApps = listOf(
    AppItem(R.drawable.ic_calendar, "Календарь", "Планируйте дела", "Продуктивность"),
    AppItem(R.drawable.ic_weather, "Погода", "Прогноз на неделю", "Утилиты"),
    AppItem(R.drawable.ic_notes, "Заметки", "Сохраняйте идеи", "Продуктивность"),
    AppItem(R.drawable.ic_calculator, "Калькулятор", "Быстрые расчёты", "Утилиты"),
    AppItem(R.drawable.ic_timer, "Таймер", "Контроль времени", "Продуктивность"),
    AppItem(R.drawable.ic_alarm, "Будильник", "Не проспите", "Утилиты"),
    AppItem(R.drawable.ic_converter, "Конвертер", "Единицы измерения", "Утилиты"),
    AppItem(R.drawable.ic_notebook, "Блокнот", "Текстовые заметки", "Продуктивность"),
    AppItem(R.drawable.ic_clock, "Часы", "Точное время", "Утилиты")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseSection(onAppClick: (AppItem) -> Unit) {
    val pageCount = 3
    val itemsPerPage = 3
    val pagerState = rememberPagerState { pageCount }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Витрина",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 16.dp
        ) { page ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                repeat(itemsPerPage) { index ->
                    val globalIndex = page * itemsPerPage + index
                    if (globalIndex < showcaseApps.size) {
                        val app = showcaseApps[globalIndex]
                        AppListItem(
                            app = app,
                            onClick = { onAppClick(app) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(
                            if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            ShowcaseSection(onAppClick = { app -> println("Clicked: \${app.name}") })
        }
    }
}