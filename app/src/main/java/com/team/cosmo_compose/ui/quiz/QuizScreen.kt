package com.team.cosmo_compose.ui.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import com.team.cosmo_compose.R
import com.team.cosmo_compose.ui.theme.Typography

@Composable
fun QuizScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "ic_back",
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 4.dp,
                ),
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_save),
                contentDescription = "ic_save",
                modifier = Modifier.padding(all = 20.dp),
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "문제 1",
            color = colorResource(id = R.color.black),
            style = Typography.headlineLarge,
            modifier = Modifier.padding(start = 20.dp),
        )
        Spacer(modifier = Modifier.size(12.dp))
        QuizBoard()
        Spacer(modifier = Modifier.size(36.dp))
        Text(
            text = "답변",
            color = colorResource(id = R.color.black),
            style = Typography.headlineLarge,
            modifier = Modifier.padding(start = 20.dp),
        )
        Spacer(modifier = Modifier.size(12.dp))
        MultipleChoiceQuestion()
        Spacer(modifier = Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(size = 30.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_100)),
            ) {
                Text(
                    text = "답변 제출하기",
                    color = colorResource(id = R.color.white),
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(
                        horizontal = 84.dp,
                        vertical = 18.dp,
                    )
                )
            }

            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(size = 56.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.secondary_100)),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ai_bot),
                    contentDescription = "ic_ai_bot",
                    modifier = Modifier.padding(all = 16.dp),
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
    }
}

@Composable
fun MultipleChoiceQuestion() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 20.dp),
    ) {
        items(4) {
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
                    text = "운영체제가 강제로 프로세스를 중단시키고 다른 프로세스에 CPU를 할당함",
                    modifier = Modifier.padding(all = 20.dp)
                )
            }
        }
    }
}

@Composable
fun QuizBoard() {
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
            text = "오늘 학습하기 · 운영체제 · 객관식 ",
            color = colorResource(id = R.color.gray_200),
            style = Typography.labelMedium,
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
            )
        )
        Text(
            text = "Q. 다음 중 운영체제에서 메모리 단편화(Fragmentation)를 방지하는 기법은?",
            color = colorResource(id = R.color.black),
            style = Typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 12.dp,
                    bottom = 36.dp,
                )
        )
    }
}
