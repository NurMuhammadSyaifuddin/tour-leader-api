package com.nms.tourleader.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "chat")
data class Chat(

    @Id
    @Column(name = "id_chat")
    val id_chat: String?,

    @Column(name = "message")
    val message: ByteArray?,

    @Column(name = "detail")
    val detail: String?,

    @Column(name = "date")
    val date: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    val group: Group?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Chat

        if (message != null) {
            if (other.message == null) return false
            if (!message.contentEquals(other.message)) return false
        } else if (other.message != null) return false

        return true
    }

    override fun hashCode(): Int {
        return message?.contentHashCode() ?: 0
    }
}