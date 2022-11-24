package com.nms.tourleader.model

import com.nms.tourleader.entity.Chat
import com.nms.tourleader.entity.Member

data class GroupResponse    (
    val id_group: String,
    val name: String,
    val detail: String,
    val avatar: ByteArray?,
    val barcode: ByteArray?,
    val chats: MutableList<Chat>?,
    val users: MutableSet<Member>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GroupResponse

        if (avatar != null) {
            if (other.avatar == null) return false
            if (!avatar.contentEquals(other.avatar)) return false
        } else if (other.avatar != null) return false

        return true
    }

    override fun hashCode(): Int {
        return avatar?.contentHashCode() ?: 0
    }
}