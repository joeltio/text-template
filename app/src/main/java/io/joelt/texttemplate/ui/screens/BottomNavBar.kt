package io.joelt.texttemplate.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import io.joelt.texttemplate.R
import io.joelt.texttemplate.navigation.Route
import io.joelt.texttemplate.navigation.atRoute
import io.joelt.texttemplate.navigation.navigateClearStack
import io.joelt.texttemplate.ui.screens.archived.archived
import io.joelt.texttemplate.ui.screens.drafts.drafts
import io.joelt.texttemplate.ui.screens.templates.templates

private data class NavItem(
    val name: String,
    val icon: Painter,
    val route: String
)

@Composable
fun BottomNavBar(nav: NavHostController) {
    val items = listOf(
        NavItem(
            stringResource(id = R.string.navbar_templates),
            painterResource(id = R.drawable.ic_baseline_description_24),
            Route.templates
        ),
        NavItem(
            stringResource(id = R.string.navbar_drafts),
            painterResource(id = R.drawable.ic_baseline_drafts_24),
            Route.drafts
        ),
        NavItem(
            stringResource(id = R.string.navbar_archived),
            painterResource(id = R.drawable.ic_baseline_inventory_24),
            Route.archived
        )
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.name
                    )
                },
                label = { Text(text = item.name) },
                selected = nav.atRoute(item.route),
                onClick = {
                    nav.navigateClearStack(item.route)
                }
            )
        }
    }
}
