package com.nms.tourleader.controller

import com.nms.tourleader.model.*
import com.nms.tourleader.service.GroupService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class GroupController(val groupService: GroupService) {

    @PostMapping(
        value = ["/api/groups"],
        produces = ["application/json"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun createGroup(body: CreateGroupRequest, @RequestParam("avatar") avatar: MultipartFile?, @RequestParam("qrcode") qrcode: MultipartFile?): WebResponse<GroupResponse>{
        val response = groupService.create(body, avatar, qrcode)

        return webResponse(response)
    }

    @GetMapping(
        value = ["/api/groups/{idGroup}"],
        produces = ["application/json"]
    )
    fun getGroup(@PathVariable("idGroup") id: String): WebResponse<GroupResponse>{
        val response = groupService.getGroup(id)

        return webResponse(response)
    }

    @PutMapping(
        value = ["/api/groups/{idGroup}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateGroup(@PathVariable("idGroup") id: String, @RequestBody updateGroupRequest: UpdateGroupRequest): WebResponse<GroupResponse>{
        val response = groupService.updateGroup(id, updateGroupRequest)

        return webResponse(response)
    }


    @PutMapping(
        value = ["/api/groups/{idGroup}/avatar"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = ["application/json"]
    )
    fun updateAvatarGroup(@PathVariable("idGroup") idGroup: String, @RequestParam("avatar") avatar: MultipartFile): WebResponse<GroupResponse>{
        val response = groupService.updateAvatarGroup(idGroup, avatar)

        return webResponse(response)
    }

    @PutMapping(
        value = ["/api/groups/{idGroup}/qrcode"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = ["application/json"]
    )
    fun updateQrCodeGroup(@PathVariable("idGroup") idGroup: String, @RequestParam("qrcode") qrcode: MultipartFile): WebResponse<GroupResponse>{
        val response = groupService.updateQrCodeGroup(idGroup, qrcode)

        return webResponse(response)
    }

    @PutMapping(
        value = ["/api/groups/{idGroup}/users"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateUser(@PathVariable("idGroup") id: String, @RequestBody updateMemberRequest: UpdateMemberRequest): WebResponse<GroupResponse>{
        val response = groupService.updateUser(id, updateMemberRequest)

        return webResponse(response)
    }

    @DeleteMapping(
        value = ["/api/groups/{idGroup}"],
        produces = ["application/json"]
    )
    fun deleteGroup(@PathVariable("idGroup") id: String): WebResponse<String> {
        groupService.deleteGroup(id)
        return webResponse(id)
    }

    @GetMapping(
        value = ["/api/groups"],
        produces = ["application/json"]
    )
    fun listGroup(
    ): WebResponse<List<GroupResponse>>{
        val responses = groupService.listGroup()
        return webResponse(responses)
    }

    @DeleteMapping(
        value = ["/api/groups/users/{idUser}"],
        produces = ["application/json"]
    )
    fun deleteUserFromAllGroup(@PathVariable("idUser") id: String): WebResponse<String>{
        groupService.deleteUserFromAllGroup(id)
        return webResponse("User leave in all group")
    }

    @DeleteMapping(
        value = ["/api/groups/{idGroup}/users/{idUser}"],
        produces = ["application/json"]
    )
    fun deleteUserFromGroup(@PathVariable("idGroup") idGroup: String, @PathVariable("idUser") idUser: String): WebResponse<String>{
        groupService.deleteUserFromGroup(idGroup, idUser)
        return webResponse("User leave in group with id $idGroup")
    }


    @GetMapping(
        value = ["/api/groups/users/{idUser}"],
        produces = ["application/json"]
    )
    fun listGroupUser(@PathVariable("idUser") idUser: String): WebResponse<List<GroupResponse>>{
        val responses = groupService.listGroupUser(idUser)
        return webResponse(responses)
    }

    @PutMapping(
        value = ["/api/groups/{idGroup}/chats"],
        produces = ["application/json"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun updateChat(@PathVariable("idGroup") id: String, chatRequest: ChatRequest, @RequestParam("message") message: MultipartFile): WebResponse<GroupResponse>{
        val response = groupService.updateChatGroup(id, chatRequest, message)

        return webResponse(response)
    }

    @DeleteMapping(
        value = ["/api/groups/{idGroup}/chats/{idChat}"],
        produces = ["application/json"]
    )
    fun deleteChatGroup(@PathVariable("idGroup") idGroup: String, @PathVariable("idChat") idChat: String): WebResponse<String>{
        groupService.deleteChat(idGroup, idChat)
        return webResponse("$idChat is deleted")
    }

    @DeleteMapping(
        value = ["/api/groups/{idGroup}/chats"],
        produces = ["application/json"]
    )
    fun deleteAllChatGroup(@PathVariable("idGroup") idGroup: String): WebResponse<String>{
        groupService.deleteAllChat(idGroup)
        return webResponse("All chat in group $idGroup is deleted")
    }

    private fun <T> webResponse(response: T) =
        WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
}