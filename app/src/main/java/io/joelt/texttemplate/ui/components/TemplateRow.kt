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
import io.joelt.texttemplate.models.Either
import io.joelt.texttemplate.models.slots.Slot
import io.joelt.texttemplate.models.toTemplateSlot
import io.joelt.texttemplate.ui.theme.TextTemplateTheme
import io.joelt.texttemplate.ui.theme.Typography
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")

@Composable
fun TemplateRow(
    name: String,
    slots: List<Either<String, Slot>>,
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
        SlotsPreview(
            slots = slots,
            style = Typography.bodyMedium,
            maxLines = 3
        )

        dateTime?.let {
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = dateTime.format(dateTimeFormat),
                style = Typography.labelMedium,
                modifier = Modifier.alpha(0.38f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TemplateRowExample() {
    val name = "My Template"
    val slots = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
        eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
        ad minim veniam, quis nostrud exercitation ullamco laboris
        {% text | label="Name" %}nisi ut aliquip ex{% end %} ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit
        esse cillum dolore eu
        {% text | label="Location" %}fugiat nulla pariatur.{% end %}
    """.trimIndent().replace("\n", " ").toTemplateSlot()

    TextTemplateTheme {
        Column {
            TemplateRow(name, slots) {}
            Divider()
            TemplateRow(name, slots, dateTime = LocalDateTime.now()) {}
            Divider()
            TemplateRow(name, slots, dateTime = LocalDateTime.now()) {}
            Divider()
            TemplateRow(name, slots, dateTime = LocalDateTime.now()) {}
        }
    }
}