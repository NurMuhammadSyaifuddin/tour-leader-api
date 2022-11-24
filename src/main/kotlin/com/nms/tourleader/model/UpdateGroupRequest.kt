package com.nms.tourleader.model

import javax.validation.constraints.NotBlank

data class UpdateGroupRequest(

    @field:NotBlank
    val name: String?,

    @field:NotBlank
    val detail: String?

)