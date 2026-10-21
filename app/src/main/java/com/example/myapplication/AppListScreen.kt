package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.foundation.Image

data class AppItem(
    val iconId: Int,
    val name: String,
    val description: String,
    val category: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(onAppClick: (AppItem) -> Unit = {}) {
    val itemsList = listOf(
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

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Apps,
                        contentDescription = "Меню"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF6200EE),
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
            items(itemsList) { app ->
                AppListItem(
                    app = app,
                    onClick = { onAppClick(app) }  // <--- ИСПРАВЛЕНО: используется переданный параметр
                )
            }
        }
    }
}

@Composable
fun AppListItem(
    app: AppItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(app.iconId),
                contentDescription = app.name,
                modifier = Modifier.size(48.dp).clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = app.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = app.category,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppListScreenPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            AppListScreen()
        }
    }
}
