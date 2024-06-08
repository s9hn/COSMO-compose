package com.team.cosmo_compose.ui.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.team.cosmo_compose.ui.quiz.model.Fixture
import com.team.cosmo_compose.ui.quiz.model.QuizModel
import com.team.cosmo_compose.ui.theme.COSMOcomposeTheme
import com.team.cosmo_compose.ui.theme.Typography

@Composable
fun QuizScreen(
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        QuizTopAppBar(
            onBackClick = onBackClick,
            onSaveClick = {},
        )
        Spacer(modifier = Modifier.size(8.dp))
        QuizQuestion(quizModel = Fixture)
        Spacer(modifier = Modifier.size(36.dp))
        QuizAnswer(quizModel = Fixture)
        Spacer(modifier = Modifier.weight(1f))
        QuizBottomAppBar(
            onProgressQuizClick = {},
            onHelperClick = {},
        )
        Spacer(modifier = Modifier.size(20.dp))
    }
}

@Composable
private fun QuizTopAppBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = stringResource(R.string.quiz_ic_back),
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    top = 4.dp,
                )
                .clickable(onClick = onBackClick),
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_save),
            contentDescription = stringResource(R.string.quiz_ic_save),
            modifier = Modifier
                .padding(all = 20.dp)
                .clickable(onClick = onSaveClick),
        )
    }
}

@Composable
private fun QuizQuestion(
    quizModel: QuizModel,
) {
    Text(
        text = "문제 1",
        color = colorResource(id = R.color.black),
        style = Typography.headlineLarge,
        modifier = Modifier.padding(start = 20.dp),
    )
    Spacer(modifier = Modifier.size(12.dp))
    QuizQuestionBoard(quizModel = quizModel)
}

@Composable
private fun QuizQuestionBoard(
    quizModel: QuizModel,
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
            text = quizModel.getQuestionInfo(),
            color = colorResource(id = R.color.gray_200),
            style = Typography.labelMedium,
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
            ),
        )
        Text(
            text = quizModel.question,
            color = colorResource(id = R.color.black),
            style = Typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 12.dp,
                    bottom = 36.dp,
                ),
        )
    }
}

@Composable
private fun QuizAnswer(
    quizModel: QuizModel,
) {
    Text(
        text = stringResource(R.string.quiz_answer_title),
        color = colorResource(id = R.color.black),
        style = Typography.headlineLarge,
        modifier = Modifier.padding(start = 20.dp),
    )
    Spacer(modifier = Modifier.size(12.dp))
    MultipleChoiceQuestion(quizModel = quizModel)
}

@Composable
fun MultipleChoiceQuestion(
    quizModel: QuizModel,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 20.dp),
    ) {
        items(quizModel.choices.size) { index ->
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
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
                Text(
                    text = quizModel.choices[index],
                    modifier = Modifier.padding(all = 20.dp),
                )
            }
        }
    }
}

@Composable
private fun QuizBottomAppBar(
    onProgressQuizClick: () -> Unit,
    onHelperClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Button(
            onClick = onProgressQuizClick,
            shape = RoundedCornerShape(size = 30.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_100)),
        ) {
            Text(
                text = stringResource(R.string.quiz_progress_quiz_button),
                color = colorResource(id = R.color.white),
                style = Typography.titleLarge,
                modifier = Modifier.padding(
                    horizontal = 84.dp,
                    vertical = 18.dp,
                ),
            )
        }
        Button(
            onClick = onHelperClick,
            shape = RoundedCornerShape(size = 56.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.secondary_100)),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_ai_bot),
                contentDescription = stringResource(R.string.quiz_ic_ai_bot),
                modifier = Modifier.padding(all = 16.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuizScreenPreview() {
    COSMOcomposeTheme {
        QuizScreen({})
    }
}