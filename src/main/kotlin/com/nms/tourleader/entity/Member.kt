package com.nms.tourleader.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "member")
data class Member (

    @Id
    @Column(name = "id_member")
    val id_member: String?,

    @Column(name = "date")
    val date: String?,

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE], mappedBy = "users")
    @JsonIgnore
    val groups: MutableSet<Group>
)