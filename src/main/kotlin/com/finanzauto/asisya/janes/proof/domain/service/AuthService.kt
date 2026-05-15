package com.finanzauto.asisya.janes.proof.domain.service

import com.finanzauto.asisya.janes.proof.configuration.JwtService
import com.finanzauto.asisya.janes.proof.application.request.AuthRequest
import com.finanzauto.asisya.janes.proof.application.request.LoginRequest
import com.finanzauto.asisya.janes.proof.application.response.TokenAuthResponse
import com.finanzauto.asisya.janes.proof.application.response.UsersResponse
import com.finanzauto.asisya.janes.proof.domain.repository.AuthRepository
import com.finanzauto.asisya.janes.proof.application.use_case.AuthUseCase
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authRepository: AuthRepository,
    private val jwtService: JwtService
): AuthUseCase {

    override fun findByPersonEmail( email: String): UsersResponse? {
        return authRepository.findByPersonEmail(email)
    }

    override fun findByEmail( email: String): UsersResponse? {
        return authRepository.findByEmail(email)
    }

    override fun findAll(): List<UsersResponse?> {
        return authRepository.findAll()
    }

    override fun finById(id: Long): UsersResponse? {
       return authRepository.finById(id)
    }

    override fun signup(input: AuthRequest): UsersResponse? {
        return authRepository.signup(input)
    }
    override fun authenticate(input: LoginRequest): TokenAuthResponse?{
        val authenticatedUser = authRepository.authenticate(input)
        val jwtToken = jwtService.generateToken(authenticatedUser?.email.toString())
        val loginResponse = TokenAuthResponse(
            jwtToken,
            "Bearer",
            jwtService.getExpirationTime()
        )
        return loginResponse
    }
}