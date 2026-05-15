package com.finanzauto.asisya.janes.proof.presentation.controller

import com.finanzauto.asisya.janes.proof.application.request.AuthRequest
import com.finanzauto.asisya.janes.proof.application.request.LoginRequest
import com.finanzauto.asisya.janes.proof.application.response.TokenAuthResponse
import com.finanzauto.asisya.janes.proof.application.response.UsersResponse
import com.finanzauto.asisya.janes.proof.domain.exception.UsersNotExit
import com.finanzauto.asisya.janes.proof.application.use_case.AuthUseCase
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
// Se remueve el slash final para estandarizar y se añade versionamiento
@RequestMapping("/api/v1/users")
@Tag(name = "User & Auth Controller", description = "Operaciones de autenticación y gestión de usuarios")
class AuthController(
    // Inyectamos el Caso de Uso (o Servicio de Aplicación), no el Repositorio
    private val authUseCase: AuthUseCase
) {
    private val logger = LoggerFactory.getLogger(AuthController::class.java)

    // Se cambia "/all" por "/" (En REST, GET /users devuelve todos)
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UsersResponse?>> {
        return ResponseEntity.ok(authUseCase.findAll())
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UsersResponse> {
        // Uso del Operador Elvis (?:) para un código limpio y seguro
        val user = authUseCase.finById(id)
            ?: throw UsersNotExit("El usuario con ID $id no existe")

        return ResponseEntity.ok(user)
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: AuthRequest): ResponseEntity<UsersResponse> {
        val user = authUseCase.signup(request)
            ?: throw UsersNotExit("No se pudo registrar el usuario")

        // POST debe retornar 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<TokenAuthResponse> {
        // NUNCA loguear el request completo en un login. Solo un identificador seguro.
        logger.info("Procesando intento de login para el email: {}", request.email)

        // Las validaciones de "fallo de autenticación" deberían estar encapsuladas
        // dentro del UseCase, el cual arrojará una excepción (BadCredentialsException) si falla.
        val auth = authUseCase.authenticate(request)
        return ResponseEntity.ok(auth)
    }

    // Se corrigió el mapeo de la variable de la URL ({email} empata con email: String)
    @GetMapping("/email/{email}")
    fun findByEmail(@PathVariable email: String): ResponseEntity<UsersResponse> {
        val user = authUseCase.findByEmail(email)
            ?: throw UsersNotExit("El usuario con email $email no existe")

        return ResponseEntity.ok(user)
    }
}