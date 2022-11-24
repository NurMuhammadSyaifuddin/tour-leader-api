package com.nms.tourleader.model

import javax.validation.constraints.NotNull

data class UpdateChatRequest(
    @field:NotNull
    val chats: MutableList<ChatRequest>
)
