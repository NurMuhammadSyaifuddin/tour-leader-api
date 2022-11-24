package com.nms.tourleader.model

import javax.validation.constraints.NotBlank

data class MemberRequest(
    @field:NotBlank
    val id_member: String?,

    @field:NotBlank
    val date: String?
)
