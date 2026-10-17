package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Apps
import androidx.compose.ui.draw.clip

data class AppItem(
    val iconId: Int,
    val name: String,
    val description: String,
    val category: String
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen() {
    val itemsList = listOf(
        AppItem(iconId = 0, name = "Календарь", description = "Планируйте дела и встречи", category = "Продуктивность"),
        AppItem(iconId = 0, name = "Погода", description = "Прогноз на сегодня и неделю", category = "Утилиты"),
        AppItem(iconId = 0, name = "Заметки", description = "Сохраняйте идеи и списки", category = "Продуктивность"),
        AppItem(iconId = 0, name = "Калькулятор", description = "Быстрые расчёты и конвертация", category = "Утилиты")
    )
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Apps,
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
            items(itemsList.size) {index ->
                AppListItem(app = itemsList[index])
            }
        }
    }
}

@Composable
fun AppListItem(app: AppItem) {
    Card() {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Box(modifier = Modifier.size(48.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFF6200EE))) {}
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
                Text(
                    text = app.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

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
fun Preview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White // Явный белый фон вместо цвета из темы
        ) {
            AppListScreen()
        }
    }
}