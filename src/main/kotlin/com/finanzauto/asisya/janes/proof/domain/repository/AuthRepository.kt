package com.finanzauto.asisya.janes.proof.domain.repository

import com.finanzauto.asisya.janes.proof.data.entity.UsersEntity
import com.finanzauto.asisya.janes.proof.application.request.AuthRequest
import com.finanzauto.asisya.janes.proof.application.request.LoginRequest
import com.finanzauto.asisya.janes.proof.application.response.UsersResponse

interface AuthRepository {
    fun findByPersonEmail(email: String): UsersResponse
    fun findByEmail( email: String): UsersResponse
    fun findAll(): List<UsersResponse?>
    fun finById(id: Long): UsersResponse?
    fun signup(input: AuthRequest): UsersResponse?
    fun authenticate(input: LoginRequest): UsersEntity?
}