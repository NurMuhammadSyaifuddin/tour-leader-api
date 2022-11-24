package com.nms.tourleader.controller

import com.nms.tourleader.error.NotFoundException
import com.nms.tourleader.error.UnAuthorizedException
import com.nms.tourleader.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String> =
        WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message.toString()
        )

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundException(exception: NotFoundException): WebResponse<String> =
        WebResponse(
            code = 404,
            status = "Not Found",
            data = "not found"
        )

    @ExceptionHandler(value = [UnAuthorizedException::class])
    fun unAuthorizedException(exception: UnAuthorizedException): WebResponse<String> =
        WebResponse(
            code = 401,
            status = "UNAUTHORIZED",
            data = "please put your api key"
        )

}