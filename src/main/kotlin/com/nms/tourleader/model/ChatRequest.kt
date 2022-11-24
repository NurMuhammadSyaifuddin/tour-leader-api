package com.nms.tourleader.model

import javax.validation.constraints.NotBlank

data class ChatRequest(
    @field:NotBlank
    val id_chat: String?,

    @field:NotBlank
    val detail: String?,

    @field:NotBlank
    val date: String?
)