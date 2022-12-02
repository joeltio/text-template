package io.joelt.texttemplate.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.joelt.texttemplate.database.HourFormat
import io.joelt.texttemplate.database.LocalPreferences
import io.joelt.texttemplate.models.Either
import io.joelt.texttemplate.models.genTemplates
import io.joelt.texttemplate.models.slots.Slot
import io.joelt.texttemplate.ui.theme.TextTemplateTheme
import io.joelt.texttemplate.ui.theme.Typography
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun formatDateTime(dateTime: LocalDateTime): String {
    val preferences = LocalPreferences.current
    val timeFormat = when (preferences.hourFormat) {
        HourFormat.HOUR_24 -> "H:mm"
        HourFormat.HOUR_12 -> "h:mm a"
    }

    val currentDateTime = LocalDateTime.now()
    val dateTimeFormat = if (currentDateTime.dayOfMonth == dateTime.dayOfMonth) {
        // It is today, show the time only
        DateTimeFormatter.ofPattern(timeFormat)
    } else {
        DateTimeFormatter.ofPattern("dd/MM/yy")
    }

    return dateTimeFormat.format(dateTime)
}

@Composable
fun TemplateRow(
    name: String,
    body: List<Either<String, Slot>>,
    modifier: Modifier = Modifier,
    dateTime: LocalDateTime? = null,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = name,
            style = Typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        TemplateBodyPreview(
            body = body,
            style = Typography.bodyMedium,
            maxLines = 3
        )

        dateTime?.let {
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = formatDateTime(it),
                style = Typography.labelMedium,
                modifier = Modifier.alpha(0.38f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TemplateRowExample() {
    TextTemplateTheme {
        Column {
            genTemplates(4).forEach {
                TemplateRow(it.name, it.body)
                Divider()
            }
        }
    }
}
