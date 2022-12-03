package com.nms.tourleader.service.impl

import com.nms.tourleader.entity.Chat
import com.nms.tourleader.entity.Group
import com.nms.tourleader.entity.Member
import com.nms.tourleader.error.NotFoundException
import com.nms.tourleader.model.*
import com.nms.tourleader.repository.GroupRepository
import com.nms.tourleader.service.GroupService
import com.nms.tourleader.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class GroupServiceImpl(
    val groupRepository: GroupRepository,
    val validationUtil: ValidationUtil
): GroupService {
    override fun create(
        createGroupRequest: CreateGroupRequest,
        avatar: MultipartFile?,
        qrcode: MultipartFile?
    ): GroupResponse {
        validationUtil.validate(createGroupRequest)

        val group = Group(
            idGroup = createGroupRequest.id_group!!,
            name = createGroupRequest.name!!,
            detail = createGroupRequest.detail!!,
            avatar = avatar?.bytes,
            qrcode = qrcode?.bytes,
            chats = null,
            users = null
        )

        groupRepository.save(group)

        return convertGroupToGroupResponse(group)
    }

    override fun getGroup(id: String): GroupResponse {
        val group = findGroupByIdOrThrowNotFound(id)

        return convertGroupToGroupResponse(group)
    }

    override fun updateGroup(id: String, updateGroupRequest: UpdateGroupRequest): GroupResponse {
        validationUtil.validate(updateGroupRequest)

        val group = findGroupByIdOrThrowNotFound(id)

        group.apply {
            name = updateGroupRequest.name.toString()
            detail = updateGroupRequest.detail.toString()
        }

        groupRepository.save(group)

        return convertGroupToGroupResponse(group)
    }

    override fun updateUser(idGroup: String, updateMemberRequest: UpdateMemberRequest): GroupResponse {
        validationUtil.validate(updateMemberRequest.users[0])

        val group = findGroupByIdOrThrowNotFound(idGroup)

        group.users?.add(Member(updateMemberRequest.users[0].id_member, updateMemberRequest.users[0].date, mutableSetOf(group)))

        groupRepository.save(group)

        return convertGroupToGroupResponse(group)
    }

    override fun updateAvatarGroup(idGroup: String, avatar: MultipartFile?): GroupResponse {
        val group = findGroupByIdOrThrowNotFound(idGroup)
        group.avatar = avatar?.bytes
        groupRepository.save(group)

        return convertGroupToGroupResponse(group)
    }

    override fun updateQrCodeGroup(idGroup: String, qrcode: MultipartFile?): GroupResponse {
        val group = findGroupByIdOrThrowNotFound(idGroup)
        group.qrcode = qrcode?.bytes
        groupRepository.save(group)

        return convertGroupToGroupResponse(group)
    }

    override fun deleteGroup(id: String) {
        val group = findGroupByIdOrThrowNotFound(id)
        groupRepository.delete(group)
    }

    override fun listGroup(): List<GroupResponse> {
        val groups = groupRepository.findAll()

        return groups.map { convertGroupToGroupResponse(it) }
    }

    override fun deleteUserFromAllGroup(idUser: String) {
        val newGroup = mutableListOf<Group>()
        groupRepository.findAll().forEach { group ->
            group.users?.removeIf { member ->
                member.id_member == idUser
            }
            newGroup.add(group)
        }

        groupRepository.saveAll(newGroup)

    }

    override fun deleteUserFromGroup(idGroup: String, idUser: String) {
        val group = findGroupByIdOrThrowNotFound(idGroup)

        group.users?.removeIf { member ->
            member.id_member == idUser
        }

        groupRepository.save(group)
    }

    override fun listGroupUser(idUser: String): List<GroupResponse> {

        val newGroup = mutableListOf<Group>()
        groupRepository.findAll().forEach { group ->
            group.users?.filter { member ->
                if(member.id_member == idUser) {
                    newGroup.add(group)
                    true
                } else false
            }
        }

        return newGroup.map { convertGroupToGroupResponse(it) }
    }

    override fun updateChatGroup(idGroup: String, chatRequest: ChatRequest, message: MultipartFile): GroupResponse {
        validationUtil.validate(chatRequest)

        val group = findGroupByIdOrThrowNotFound(idGroup)

        group.chats?.add(Chat(chatRequest.id_chat, message.bytes, chatRequest.detail, chatRequest.date, group))

        groupRepository.save(group)

        return convertGroupToGroupResponse(group)
    }

    override fun deleteChat(idGroup: String, idChat: String) {
        val group = findGroupByIdOrThrowNotFound(idGroup)

        group.chats?.removeIf { chat ->
            chat.id_chat == idChat
        }

        groupRepository.save(group)
    }

    override fun deleteAllChat(idGroup: String) {
        val group = findGroupByIdOrThrowNotFound(idGroup)

        group.chats?.clear()

        groupRepository.save(group)
    }

    private fun findGroupByIdOrThrowNotFound(id: String): Group =
         groupRepository.findByIdOrNull(id) ?: throw NotFoundException()

    private fun convertGroupToGroupResponse(group: Group): GroupResponse =
        GroupResponse(
            id_group = group.idGroup,
            name = group.name,
            detail = group.detail,
            avatar = group.avatar,
            qrcode = group.qrcode,
            chats = group.chats,
            users = group.users
        )
}