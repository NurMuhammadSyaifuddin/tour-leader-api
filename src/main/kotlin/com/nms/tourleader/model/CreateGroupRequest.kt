package com.nms.tourleader.model

import javax.validation.constraints.NotBlank

data class CreateGroupRequest (
    @field:NotBlank
    val id_group: String?,

    @field:NotBlank
    val name: String?,

    @field:NotBlank
    val detail: String?,

)