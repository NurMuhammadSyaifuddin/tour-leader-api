package com.nms.tourleader.model

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateGroupRequest (
    @field:NotBlank
    val id_group: String?,

    @field:NotBlank
    val name: String?,

    @field:NotBlank
    val detail: String?,

)