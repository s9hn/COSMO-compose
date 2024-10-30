package kw.team.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kw.team.designsystem.theme.CosmoTheme
import kw.team.designsystem.theme.CosmoTheme.colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CosmoAiChatBottomSheet(
    onDismissRequest: () -> Unit,
    onTabClick: (selectedTabIndex: Int) -> Unit,
    bottomSheetState: SheetState,
    pagerState: PagerState,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
) {
    val currentDensity = LocalDensity.current
    val currentConfiguration = LocalConfiguration.current
    val topPadding = remember {
        with(currentDensity) {
            currentConfiguration.screenHeightDp.dp.toPx() * 0.04
        }
    }

    ModalBottomSheet(
        modifier = modifier.padding(top = topPadding.dp),
        sheetState = bottomSheetState,
        onDismissRequest = onDismissRequest,
        containerColor = colors.onSurface50,
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
        ),
    ) {
        CosmoAiChatBottomSheetTabRow(
            onTabClick = onTabClick,
            selectedTabIndex = selectedTabIndex,
        )
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn {
                    items(listOf("123123", "123123", "123123", "123123")) {
                        Text(text = it)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun CosmoAiChatBottomSheetPreview() {
    CosmoTheme {
        CosmoAiChatBottomSheet(
            onDismissRequest = {},
            onTabClick = {},
            bottomSheetState = rememberStandardBottomSheetState(),
            pagerState = rememberPagerState(pageCount = { 2 }),
            selectedTabIndex = 0,
        )
    }
}
