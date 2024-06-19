package com.team.cosmo_compose.ui.chat.model

import androidx.compose.runtime.Stable

@Stable
data class ChatsModel(
    val sender: String,
    val chats: List<ChatModel>,
) {

    @Stable
    data class ChatModel(
        val isMe: Boolean,
        val content: String,
    )
}


