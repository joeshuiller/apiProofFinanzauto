package com.finanzauto.asisya.janes.proof.presentation.exception

import com.finanzauto.asisya.janes.proof.domain.exception.AccessDenied
import com.finanzauto.asisya.janes.proof.domain.exception.EmailNotExit
import com.finanzauto.asisya.janes.proof.domain.exception.InvalidJwtToken
import com.finanzauto.asisya.janes.proof.domain.exception.JwtExpired
import com.finanzauto.asisya.janes.proof.domain.exception.TaskNotExit
import com.finanzauto.asisya.janes.proof.domain.exception.UsersAuthNotSuccess
import com.finanzauto.asisya.janes.proof.domain.exception.UsersExit
import com.finanzauto.asisya.janes.proof.domain.exception.UsersListEmpty
import com.finanzauto.asisya.janes.proof.domain.exception.UsersNotExit
import jakarta.servlet.ServletException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import kotlin.toString

@RestControllerAdvice
class RestExceptionHadler {
    private val logger = LoggerFactory.getLogger(RestExceptionHadler::class.java)

    @ExceptionHandler(UsersExit::class)
    fun handleUsersExitException(ex: UsersExit): ResponseEntity<Error> {
        val errorResponse = Error(HttpStatus.UNAUTHORIZED.value(), "Error Usuarios ya existe", ex.message.toString())
        logger.error("Error Usuarios ya existe : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(UsersAuthNotSuccess::class)
    fun handleUsersAuthNotSuccessException(ex: UsersAuthNotSuccess): ResponseEntity<Error> {
        val errorResponse =
            Error(HttpStatus.UNAUTHORIZED.value(), "Error Usuario no autenticado", ex.message.toString())
        logger.error("Error Usuario no autenticado : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(UsersListEmpty::class)
    fun handleUsersListEmptyException(ex: UsersListEmpty): ResponseEntity<Error> {
        val errorResponse = Error(HttpStatus.UNAUTHORIZED.value(), "Error lista usuarios vacia", ex.message.toString())
        logger.error("Error lista usuarios vacia : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(EmailNotExit::class)
    fun handleEmailNotExitException(ex: EmailNotExit): ResponseEntity<Error> {
        val errorResponse = Error(HttpStatus.UNAUTHORIZED.value(), "Error Email", ex.message.toString())
        logger.error("Error Email : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(UsersNotExit::class)
    fun handleUsersNotExitException(ex: UsersNotExit): ResponseEntity<Error> {
        val errorResponse = Error(HttpStatus.UNAUTHORIZED.value(), "Error usuario no existe", ex.message.toString())
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    // Generic handler for all other RuntimeExceptions
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<Error> {
        // Log the exception details
        logger.error("Error Generics uno : {}", ex.toString())
        val errorResponse = Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error Generics", ex.message.toString())
        logger.error("Error Generics : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    // Generic handler for all other RuntimeExceptions
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Error> {
        // Log the exception details
        val errorResponse = Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error General", ex.message.toString())
        logger.error("Error General : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodException(ex: MethodArgumentNotValidException): ResponseEntity<List<Error>> {
        val errors = ArrayList<Error>()
        ex.bindingResult.fieldErrors.stream().map { err ->
            errors.add(
                Error(
                    HttpStatus.UNAUTHORIZED.value(),
                    err.field,
                    err.defaultMessage.toString()

                )
            )
        }
        logger.error("Errores : {}", errors)
        return ResponseEntity(errors, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(InvalidJwtToken::class)
    fun handleInvalidJwt(ex: InvalidJwtToken): ResponseEntity<Error>  {
        val errorResponse =
            Error(HttpStatus.UNAUTHORIZED.value(), "Error token invalido", ex.message.toString())
        logger.error("Error token invalido : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(JwtExpired::class)
    fun handleJwtExpired(ex: JwtExpired): ResponseEntity<Error>  {
        val errorResponse =
            Error(HttpStatus.UNAUTHORIZED.value(), "Error token expiro", ex.message.toString())
        logger.error("Error token expiro : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    // Manejar otras excepciones como falta de rol (403)
    @ExceptionHandler(AccessDenied::class) // O tu excepción de falta de rol
    fun handleAccessDenied(ex: AccessDenied): ResponseEntity<Error>  {
        val errorResponse =
            Error(HttpStatus.FORBIDDEN.value(), "Error token", ex.message.toString())
        logger.error("Error token : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    // Manejar otras excepciones como falta de rol (403)
    @ExceptionHandler(ServletException::class) // O tu excepción de falta de rol
    fun handleServletException(ex: ServletException): ResponseEntity<Error>  {
        val errorResponse =
            Error(HttpStatus.UNAUTHORIZED.value(), "Error ServletException", ex.message.toString())
        logger.error("Error ServletException : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(TaskNotExit::class)
    fun handleTaskNotExit(ex: TaskNotExit): ResponseEntity<Error>  {
        val errorResponse =
            Error(HttpStatus.UNAUTHORIZED.value(), "Error TaskNotExit", ex.message.toString())
        logger.error("Error TaskNotExit : {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

}

