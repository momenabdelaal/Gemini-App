package com.mobly.app.core.base.chat

sealed class Message {
    data class UserMessage(val text: String) : Message()
    data class GeminiMessage(val text: String) : Message()
}