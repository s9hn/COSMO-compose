package kw.team.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import kw.team.designsystem.component.CosmoAiChatBottomSheet
import kw.team.designsystem.component.CosmoAiChatButton
import kw.team.main.component.MainBottomAppBar
import kw.team.main.component.MainNavHost
import kw.team.main.model.MainBottomAppBarTab
import kw.team.main.model.MainBottomAppBarTab.HOME

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen() {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val pagerState = rememberPagerState(pageCount = { 2 })
    var currentTabIndex by remember { mutableIntStateOf(pagerState.currentPage) }
    var isShowBottomSheet by remember { mutableStateOf(false) }
    var currentTab by remember { mutableStateOf(HOME) }

    Scaffold(
        bottomBar = {
            MainBottomAppBar(
                currentTab = currentTab,
                tabs = MainBottomAppBarTab.entries,
                onTabClick = { selectedTab ->
                    currentTab = selectedTab
                },
            )
        },
        floatingActionButton = {
            CosmoAiChatButton(
                onClick = { isShowBottomSheet = true },
                modifier = Modifier.offset(y = 44.dp).padding(bottom = 32.dp),
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
        ) {
            MainNavHost(navController = rememberNavController())

            if (isShowBottomSheet) {
                CosmoAiChatBottomSheet(
                    onDismissRequest = { isShowBottomSheet = false },
                    onTabClick = { selectedTabIndex ->
                        scope.launch {
                            pagerState.animateScrollToPage(selectedTabIndex)
                        }
                        currentTabIndex = selectedTabIndex
                    },
                    bottomSheetState = bottomSheetState,
                    pagerState = pagerState,
                    selectedTabIndex = currentTabIndex,
                )
            }
        }
    }
}
