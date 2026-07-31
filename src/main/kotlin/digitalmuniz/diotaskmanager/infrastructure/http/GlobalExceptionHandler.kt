package digitalmuniz.diotaskmanager.infrastructure.http

import digitalmuniz.diotaskmanager.domain.TaskNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): Map<String, String?> {
        val errors = HashMap<String, String?>()

        ex.bindingResult.allErrors.forEach {
            val fieldName = (it as FieldError).field
            val errorMessage = it.defaultMessage
            errors[fieldName] = errorMessage
        }
        return errors
    }
}