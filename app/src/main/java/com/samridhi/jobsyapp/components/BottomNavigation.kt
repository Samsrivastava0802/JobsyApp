package com.samridhi.jobsyapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samridhi.jobsyapp.alias.AppDrawable
import com.samridhi.jobsyapp.alias.AppString
import com.samridhi.jobsyapp.navigation.AppHomeFlow
import com.samridhi.jobsyapp.ui.theme.black
import com.samridhi.jobsyapp.ui.theme.label
import com.samridhi.jobsyapp.ui.theme.lightGrey
import com.samridhi.jobsyapp.ui.theme.red
import com.samridhi.jobsyapp.ui.theme.white

@Composable
fun BottomNavigation(
    route: String,
    item: List<BottomNavItem> = listOf(
        BottomNavItem.Jobs,
        BottomNavItem.Bookmark
    ),
    onClick: (selectedTab: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.forEach { item ->
            val activeTab = item.route == route
            BottomNavigationItem(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onClick(item.route)
                    },
                item = item,
                isSelected = activeTab
            )
        }
    }
}

@Composable
fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    item: BottomNavItem,
    isSelected: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = stringResource(id = item.title),
            tint = if (isSelected) red else lightGrey,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = stringResource(id = item.title),
            style = MaterialTheme.typography.label.copy(
                color = if (isSelected) red else lightGrey,
                fontSize = 12.sp
            ),
            maxLines = 1
        )
    }
}

sealed class BottomNavItem(
    var title: Int,
    val icon: Int,
    val route: String
) {
    data object Jobs : BottomNavItem(
        AppString.job,
        AppDrawable.suitcase,
        AppHomeFlow.JobScreen.route,
    )

    data object Bookmark : BottomNavItem(
        AppString.bookmark,
        AppDrawable.bookmark,
        AppHomeFlow.BookmarkScreen.route
    )
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    BottomNavigation(
        route = AppHomeFlow.JobScreen.route,
        item = listOf(
            BottomNavItem.Jobs,
            BottomNavItem.Bookmark,
        ),
        onClick = {}
    )
}