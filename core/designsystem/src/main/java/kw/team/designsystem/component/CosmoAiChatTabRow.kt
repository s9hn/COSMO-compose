package kw.team.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kw.team.designsystem.theme.CosmoTheme
import kw.team.designsystem.theme.CosmoTheme.colors
import kw.team.designsystem.theme.CosmoTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CosmoAiChatBottomSheetTabRow(
    onTabClick: (selectedTabIndex: Int) -> Unit,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
) {
    // need to refactor tabs
    val tabs = listOf("GPT", "클로드")
    SecondaryTabRow(
        modifier = modifier,
        divider = {
            Spacer(
                modifier = Modifier
                    .height(height = 0.6.dp)
                    .background(color = colors.onSurface100),
            )
        },
        indicator = {
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier
                    .tabIndicatorOffset(selectedTabIndex = selectedTabIndex)
                    .padding(horizontal = 54.dp),
                height = 2.dp,
                color = colors.primary,
            )
        },
        containerColor = colors.onSurface50,
        selectedTabIndex = selectedTabIndex,
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabClick(index) },
            ) {
                Text(
                    text = title,
                    style = typography.headline20M,
                    color = colors.onBackground,
                    modifier = Modifier.padding(vertical = 12.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun CosmoAiChatBottomSheetTabRowPreview() {
    CosmoTheme {
        CosmoAiChatBottomSheetTabRow(
            onTabClick = {},
            selectedTabIndex = 0,
        )
    }
}
