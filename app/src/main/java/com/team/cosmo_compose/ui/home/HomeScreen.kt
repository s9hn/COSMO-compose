package com.team.cosmo_compose.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.cosmo_compose.R
import com.team.cosmo_compose.ui.chat.ChatBotModalBottomSheetContent
import com.team.cosmo_compose.ui.home.model.subjects
import com.team.cosmo_compose.ui.theme.COSMOcomposeTheme
import com.team.cosmo_compose.ui.theme.Typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onQuizClick: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.HalfExpanded,
    )
    var state by remember { mutableStateOf(false) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    when (state) {
        true -> LaunchedEffect(Unit) { bottomSheetState.show() }
        false -> LaunchedEffect(Unit) { bottomSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            ChatBotModalBottomSheetContent(
                selectedTabIndex = selectedTabIndex,
                onTabClick = { selectedTabIndex = it },
            )
        },
        sheetBackgroundColor = colorResource(id = R.color.gray_50),
        sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
        ),
        sheetState = bottomSheetState,
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                HomeTopAppBar()
                TodayStudyBoard(
                    isLearned = true,
                    onQuizClick = onQuizClick,
                )
                QuizForSubject()
                Spacer(modifier = Modifier.weight(1f))
                HomeBottomAppBar(onChatBotClick = { state = !state }) // 옵저빙할것
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeBottomAppBar(
    onChatBotClick: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onChatBotClick,
                shape = CircleShape,
                containerColor = colorResource(id = R.color.secondary_100),
                modifier = Modifier.offset(y = 44.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ai_bot),
                    contentDescription = stringResource(R.string.quiz_ic_ai_bot),
                    modifier = Modifier.padding(all = 16.dp),
                    tint = colorResource(id = R.color.white),
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(id = R.color.white),
                modifier = Modifier
                    .navigationBarsPadding()
                    .drawBehind {
                        drawRect(
                            color = Color(R.color.primary_100),
                            size = Size(size.width, 1f),
                        )
                    },
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = stringResource(R.string.home_ic_home),
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.home_label_home),
                            style = Typography.labelMedium,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(id = R.color.black),
                        selectedTextColor = colorResource(id = R.color.black),
                        indicatorColor = colorResource(id = R.color.white),
                        unselectedTextColor = colorResource(id = R.color.gray_100),
                        unselectedIconColor = colorResource(id = R.color.gray_100),
                    ),
                    modifier = Modifier.padding(vertical = 3.dp),
                )
                Spacer(modifier = Modifier.size(120.dp))
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_folder),
                            contentDescription = stringResource(R.string.home_ic_folder),
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.home_label_folder),
                            style = Typography.labelMedium,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(id = R.color.black),
                        selectedTextColor = colorResource(id = R.color.black),
                        indicatorColor = colorResource(id = R.color.white),
                        unselectedTextColor = colorResource(id = R.color.gray_100),
                        unselectedIconColor = colorResource(id = R.color.gray_100),
                    ),
                    modifier = Modifier.padding(vertical = 3.dp),
                )
            }
        },
    ) {}
}

@Composable
private fun HomeTopAppBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(R.string.home_ic_logo),
            modifier = Modifier.padding(
                top = 20.dp,
                start = 24.dp,
                bottom = 20.dp,
            ),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = stringResource(R.string.home_ic_profile),
                modifier = Modifier.padding(all = 12.dp),
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "ic_notification",
                modifier = Modifier.padding(all = 12.dp),
            )
        }
    }
}

@Composable
private fun TodayStudyBoard(
    isLearned: Boolean,
    onQuizClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(
                color = colorResource(R.color.gray_50),
                shape = RoundedCornerShape(size = 15.dp),
            ),
    ) {
        Text(
            text = if (isLearned) stringResource(R.string.home_isLearned_true)
            else stringResource(R.string.home_isLearned_false),
            color = colorResource(id = R.color.black),
            style = Typography.titleSmall,
            modifier = Modifier.padding(
                start = 20.dp,
                top = 24.dp,
                bottom = 8.dp,
            ),
        )
        Button(
            onClick = onQuizClick,
            shape = RoundedCornerShape(size = 30.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_100)),
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 1.dp)
                .padding(all = 20.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 14.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_study),
                    contentDescription = stringResource(R.string.home_ic_study),
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(R.string.home_ic_today_learn_button),
                    color = colorResource(id = R.color.white),
                    style = Typography.titleSmall,
                )
            }
        }
    }
}

@Composable
private fun QuizForSubject() {
    Text(
        text = stringResource(R.string.home_subject_quiz_title),
        color = colorResource(id = R.color.black),
        style = Typography.headlineMedium,
        modifier = Modifier.padding(
            start = 20.dp,
            top = 48.dp,
        ),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        items(subjects) { subject ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.CenterVertically,
                ),
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.gray_70),
                        shape = RoundedCornerShape(10.dp),
                    )
                    .background(
                        color = colorResource(id = R.color.gray_20),
                        shape = RoundedCornerShape(10.dp),
                    ),
            ) {
                Icon(
                    painter = painterResource(id = subject.subjectIcon),
                    contentDescription = stringResource(R.string.home_ic_subject),
                )
                Text(
                    text = subject.subjectTitle,
                    color = colorResource(id = R.color.black),
                    style = Typography.titleSmall,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    COSMOcomposeTheme {
        HomeScreen({})
    }
}
