package io.joelt.texttemplate.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.joelt.texttemplate.ui.theme.Typography

@Composable
fun TemplateViewLayout(
    contentScrollable: Boolean = false,
    name: @Composable ColumnScope.() -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    body: @Composable ColumnScope.() -> Unit = {},
) {
    Column {
        var modifier = Modifier
            .padding(16.dp)
            .weight(1f)

        if (contentScrollable) {
            modifier = modifier.verticalScroll(rememberScrollState())
        }

        Column(modifier = modifier) {
            ProvideTextStyle(value = Typography.headlineLarge) {
                name()
            }
            Spacer(modifier = Modifier.height(16.dp))
            ProvideTextStyle(value = Typography.bodyLarge) {
                body()
            }
        }

        Box(
            modifier = Modifier
                .imePadding()
                .fillMaxWidth()
        ) {
            bottomBar()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TemplateViewLayoutPreview() {
    TemplateViewLayout(name = {
        Text(text = "Template Title")
    }, bottomBar = {
        BottomAppBar(actions = {}, floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        })
    }) {
        val text = "hello world! This is the start." + "\n".repeat(30) + "This is the end."
        Text(modifier = Modifier.weight(1f), text = text)
    }
}
