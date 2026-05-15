package com.finanzauto.asisya.janes.proof.data.repository

import com.finanzauto.asisya.janes.proof.application.request.AuthRequest
import com.finanzauto.asisya.janes.proof.application.request.LoginRequest
import com.finanzauto.asisya.janes.proof.application.response.UsersResponse
import com.finanzauto.asisya.janes.proof.data.crud.CrudAuth
import com.finanzauto.asisya.janes.proof.data.entity.UsersEntity
import com.finanzauto.asisya.janes.proof.data.mapper.UsersMapperService
import com.finanzauto.asisya.janes.proof.data.mapper.UsersResponseMapperService
import com.finanzauto.asisya.janes.proof.domain.dto.AuthDTO
import com.finanzauto.asisya.janes.proof.domain.repository.AuthRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.Collections
import java.util.stream.Collectors

@Repository
class AuthEntityRepository(
    private val usersMapper: UsersMapperService,
    private val usersResponseMapper: UsersResponseMapperService,
    private val crudAuth: CrudAuth,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
): AuthRepository {
    private val logger = LoggerFactory.getLogger(AuthEntityRepository::class.java)
    override fun findByPersonEmail(email: String): UsersResponse {
        val data = crudAuth.findByEmail(email)
        logger.info( "Consultando users valid : {}" , data)
        return usersResponseMapper.fromEntity(data)
    }

    override fun findByEmail(email: String): UsersResponse {
        logger.info( "Consultando users : {}" , email)
        val data = crudAuth.findByEmail(email)
        logger.info( "Consultando users : {}" , data)
        return usersResponseMapper.fromEntity(data)
    }

    override fun findAll(): List<UsersResponse?> {
        return crudAuth.findAll().stream().map {
                it -> usersResponseMapper.fromEntity(it)
        }.collect(Collectors.toList())
    }

    override fun finById(id: Long): UsersResponse? {
        val data = crudAuth.findById(id).orElse(null)
        logger.info( "Consultando users: {}" , data)
        return usersResponseMapper.fromEntity(data)
    }
    @Transactional
    override fun signup(input: AuthRequest): UsersResponse? {
        val user = AuthDTO(
            input.name,
            input.email,
            passwordEncoder.encode(input.password).toString(),
            Collections.singleton(SimpleGrantedAuthority("user"))
        )
        logger.info( "Procesando AuthDto: {}" , user)
        val users = usersMapper.toEntity(user)
        logger.info( "Procesando users: {}" , users)
        val save = crudAuth.save(users)
        logger.info( "save users: {}" , save)
        return usersResponseMapper.fromEntity(save)

    }

    override fun authenticate(input: LoginRequest): UsersEntity? {
        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                input.email,
                input.password
            )
        )
        logger.info( "authenticate users: {}" , auth)
        return  crudAuth.findByEmail(input.email)
    }
}