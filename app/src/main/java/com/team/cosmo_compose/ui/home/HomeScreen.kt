package com.team.cosmo_compose.ui.home

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.cosmo_compose.R
import com.team.cosmo_compose.ui.home.model.subjects
import com.team.cosmo_compose.ui.theme.COSMOcomposeTheme
import com.team.cosmo_compose.ui.theme.Typography

@Composable
fun HomeScreen(
    onQuizClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        HomeTopAppBar()
        TodayStudyBoard(
            isLearned = true,
            onQuizClick = onQuizClick,
        )
        QuizForSubject()
    }
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
