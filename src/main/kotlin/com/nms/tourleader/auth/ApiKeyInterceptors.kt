package com.nms.tourleader.auth

import com.nms.tourleader.error.UnAuthorizedException
import com.nms.tourleader.repository.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class ApiKeyInterceptors(val repository: ApiKeyRepository) : WebRequestInterceptor{
    override fun preHandle(request: WebRequest) {
        val apiKey = request.getHeader("Api-Key") ?: throw UnAuthorizedException()

        if (!repository.existsById(apiKey)) throw UnAuthorizedException()
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {

    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {

    }
}