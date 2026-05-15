package com.finanzauto.asisya.janes.proof.data.mapper

import com.finanzauto.asisya.janes.proof.data.entity.UsersEntity
import com.finanzauto.asisya.janes.proof.domain.dto.AuthDTO
import com.finanzauto.asisya.janes.proof.application.request.AuthRequest
import org.springframework.stereotype.Service

@Service
class UsersMapperService: Mapper<AuthDTO, UsersEntity, AuthRequest> {
    override fun fromEntity(entity: UsersEntity): AuthDTO {
        return AuthDTO(
            entity.name,
            entity.email,
            entity.password,
            entity.authorities
        )
    }

    override fun toEntity(domain: AuthDTO): UsersEntity {
        return UsersEntity(
            null,
            domain.name,
            domain.email,
            domain.password,
            domain.uAuthorities
        )
    }

    override fun toEntityT(domain: AuthRequest): UsersEntity {
        TODO("Not yet implemented")
    }
}