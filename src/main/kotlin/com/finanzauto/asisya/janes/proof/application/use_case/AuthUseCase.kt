package com.finanzauto.asisya.janes.proof.application.use_case

import com.finanzauto.asisya.janes.proof.application.request.AuthRequest
import com.finanzauto.asisya.janes.proof.application.request.LoginRequest
import com.finanzauto.asisya.janes.proof.application.response.TokenAuthResponse
import com.finanzauto.asisya.janes.proof.application.response.UsersResponse

interface AuthUseCase {
    fun findByPersonEmail( email: String): UsersResponse?
    fun findByEmail( email: String): UsersResponse?
    fun findAll(): List<UsersResponse?>
    fun finById(id: Long): UsersResponse?
    fun signup(input: AuthRequest): UsersResponse?
    fun authenticate(input: LoginRequest): TokenAuthResponse?
}