package com.nms.tourleader.service

import com.nms.tourleader.model.*
import org.springframework.web.multipart.MultipartFile

interface GroupService {
    fun create(createGroupRequest: CreateGroupRequest, avatar: MultipartFile?, qrcode: MultipartFile?): GroupResponse
    fun getGroup(id: String): GroupResponse
    fun updateGroup(id: String, updateGroupRequest: UpdateGroupRequest): GroupResponse
    fun updateUser(idGroup: String, updateMemberRequest: UpdateMemberRequest): GroupResponse
    fun updateAvatarGroup(idGroup: String, avatar: MultipartFile?): GroupResponse
    fun updateQrCodeGroup(idGroup: String, qrcode: MultipartFile?): GroupResponse
    fun deleteGroup(id: String)
    fun listGroup(): List<GroupResponse>
    fun deleteUserFromAllGroup(idUser: String)
    fun deleteUserFromGroup(idGroup: String, idUser: String)
    fun listGroupUser(idUser: String): List<GroupResponse>
    fun updateChatGroup(idGroup: String, chatRequest: ChatRequest, message: MultipartFile): GroupResponse
    fun deleteChat(idGroup: String, idChat: String)
    fun deleteAllChat(idGroup: String)
}