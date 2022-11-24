package com.nms.tourleader.entity

import org.hibernate.annotations.Type
import javax.persistence.*


@Entity
@Table(name = "groups")
data class Group(

    @Id
    @Column(name = "id_group")
    val idGroup: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "detail")
    var detail: String,

    @Lob
    @Column(name = "avatar")
    @Type(type="org.hibernate.type.BinaryType")
    var avatar: ByteArray?,

    @Column(name = "qrcode")
    var qrcode: ByteArray?,

    @Column(name = "chats")
    @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL], orphanRemoval = true, targetEntity = Chat::class)
    val chats: MutableList<Chat>?,

    @Column(name = "users")
    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE], targetEntity = Member::class)
    @JoinTable(
        name = "group_member",
        joinColumns = [JoinColumn(name = "group_id")],
        inverseJoinColumns = [JoinColumn(name = "member_id")]
    )
    val users: MutableSet<Member>?

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Group

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