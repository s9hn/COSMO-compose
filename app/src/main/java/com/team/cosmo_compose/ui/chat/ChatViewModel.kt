package com.team.cosmo_compose.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.team.cosmo_compose.ui.chat.model.ChatsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel : ViewModel() {

    private val _gptChats: MutableStateFlow<ChatsModel> = MutableStateFlow(
        ChatsModel(
            sender = GPT,
            chats = listOf(
                ChatsModel.ChatModel(
                    isMe = false, content = "안녕하세요! GPT-BOT입니다!\n 무엇이 궁금하신가요?"
                ),
            )
        ),
    )
    val gptChats: StateFlow<ChatsModel> = _gptChats.asStateFlow()

    private val _claudeChats: MutableStateFlow<ChatsModel> = MutableStateFlow(
        ChatsModel(
            sender = "Claude",
            chats = listOf(
                ChatsModel.ChatModel(
                    isMe = false, content = "안녕하세요! Claude-BOT입니다!\n 무엇이 궁금하신가요?"
                ),
            )
        ),
    )

    val claudeChats: StateFlow<ChatsModel> = _claudeChats.asStateFlow()


    fun sendMessage(message: String, sender: String) {
        when (sender) {
            GPT -> _gptChats.value = gptChats.value.copy(
                chats = gptChats.value.chats + ChatsModel.ChatModel(
                    isMe = true,
                    content = message,
                )
            )

            CLAUDE -> _claudeChats.value = claudeChats.value.copy(
                chats = claudeChats.value.chats + ChatsModel.ChatModel(
                    isMe = true,
                    content = message,
                )
            )
        }
    }

    companion object {
        const val GPT = "GPT"
        const val CLAUDE = "Claude"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ChatViewModel()
            }
        }
    }
}

sealed interface ChatUiState {
    object Error : ChatUiState
    object Loading : ChatUiState
    data class Success(
        val gptChats: ChatsModel,
        val claudeChats: ChatsModel,
    ) : ChatUiState
}
