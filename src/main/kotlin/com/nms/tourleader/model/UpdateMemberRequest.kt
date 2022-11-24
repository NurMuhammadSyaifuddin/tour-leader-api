package com.nms.tourleader.model

import javax.validation.constraints.NotNull

data class UpdateMemberRequest(

    @field:NotNull
    val users: MutableList<MemberRequest>
)
