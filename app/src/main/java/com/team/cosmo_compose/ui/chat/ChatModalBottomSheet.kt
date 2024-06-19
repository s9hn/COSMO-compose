package com.team.cosmo_compose.ui.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team.cosmo_compose.R
import com.team.cosmo_compose.ui.chat.model.ChatsModel
import com.team.cosmo_compose.ui.theme.COSMOcomposeTheme
import com.team.cosmo_compose.ui.theme.Typography

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun ChatBotModalBottomSheetContent(
    selectedTabIndex: Int,
    chatViewModel: ChatViewModel = viewModel(factory = ChatViewModel.Factory),
    onTabClick: (selectedTabIndex: Int) -> Unit,
) {
    val tabIndex by rememberUpdatedState(selectedTabIndex)
    val (textValue, setText) = remember { mutableStateOf("") }
    var hintVisible by remember { mutableStateOf(true) }
    val onTextChange = { text: String ->
        setText(text)
        hintVisible = text.isEmpty()
    }
    val gptChats: ChatsModel by chatViewModel.gptChats.collectAsStateWithLifecycle()
    val claudeChats: ChatsModel by chatViewModel.claudeChats.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.gray_50),
            ),
    ) {
        ChatBotTabLayout(
            selectedTabIndex = tabIndex,
            onTabClick = onTabClick,
        )
        val chatModel = when (tabIndex) {
            0 -> gptChats
            1 -> claudeChats
            else -> throw IllegalArgumentException()
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.gray_50))
                .padding(
                    horizontal = 20.dp,
                    vertical = 28.dp
                ),
        ) {
            items(
                items = chatModel.chats,
            ) { chat ->
                ChatBlock(chat)
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .offset(y = 40.dp)
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp,
                )
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.secondary_100),
                    shape = RoundedCornerShape(30.dp),
                )
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(30.dp),
                ),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(0.85f),
            ) {
                if (hintVisible) {
                    Text(
                        text = "코스모에게 질문하기",
                        color = colorResource(id = R.color.gray_100),
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 12.dp,
                        ),
                    )
                }
                BasicTextField(
                    value = textValue,
                    onValueChange = onTextChange,
                    textStyle = Typography.bodyLarge,
                    cursorBrush = SolidColor(Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 12.dp,
                        ),
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "ic_send",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        chatViewModel.sendMessage(textValue, chatModel.sender)
                    }
            )
        }
    }
}

@Composable
private fun ChatBlock(chat: ChatsModel.ChatModel) {
    val messageArrangement = if (chat.isMe) Arrangement.End else Arrangement.Start
    Row(
        horizontalArrangement = messageArrangement,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(size = 20.dp),
                ),
        ) {
            Text(
                text = chat.content,
                color = colorResource(id = R.color.black),
                style = Typography.bodyMedium,
                modifier = Modifier.padding(all = 16.dp),
            )
        }
    }
}

@Composable
private fun ChatBotTabLayout(
    selectedTabIndex: Int,
    onTabClick: (selectedTabIndex: Int) -> Unit,
) {
    val tabIndex by rememberUpdatedState(selectedTabIndex)
    val tabs: List<Int> = listOf(
        R.string.chat_bot_category_gpt,
        R.string.chat_bot_category_claude,
    )
    TabRow(
        selectedTabIndex = tabIndex,
        divider = {
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .background(colorResource(id = R.color.gray_100)),
            )
        },
        backgroundColor = colorResource(id = R.color.gray_50),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                height = 2.dp,
                color = colorResource(id = R.color.primary_100),
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[tabIndex])
                    .padding(horizontal = 54.dp),
            )
        },
        tabs = {
            tabs.forEachIndexed { index: Int, title: Int ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { if (tabIndex != index) onTabClick(index) },
                    text = {
                        Text(
                            text = stringResource(title),
                            style = Typography.headlineLarge,
                            color = if (tabIndex == index) colorResource(id = R.color.black)
                            else colorResource(id = R.color.gray_100),
                            modifier = Modifier.padding(
                                top = 36.dp,
                                bottom = 12.dp,
                            ),
                        )
                    },
                    interactionSource = MutableInteractionSource(),
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 72.dp),
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun ChatPreview() {
    COSMOcomposeTheme {
        ModalBottomSheetLayout(
            sheetContent = {
                ChatBotModalBottomSheetContent(
                    selectedTabIndex = 0,
                    onTabClick = { })
            },
            sheetBackgroundColor = colorResource(id = R.color.gray_50),
            sheetShape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
            ),
            sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.HalfExpanded),
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {

                }
            }
        )
    }
}
