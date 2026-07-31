package digitalmuniz.diotaskmanager.infrastructure.http

import digitalmuniz.diotaskmanager.domain.TaskNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleTaskNotFoundException(ex: TaskNotFoundException): String? {
        return ex.message
    }
}