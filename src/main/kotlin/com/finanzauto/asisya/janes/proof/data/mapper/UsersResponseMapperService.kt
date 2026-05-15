package com.finanzauto.asisya.janes.proof.data.mapper

import com.finanzauto.asisya.janes.proof.data.entity.UsersEntity
import com.finanzauto.asisya.janes.proof.application.request.AuthRequest
import com.finanzauto.asisya.janes.proof.application.response.UsersResponse
import org.springframework.stereotype.Service

@Service
class UsersResponseMapperService: Mapper<UsersResponse, UsersEntity, AuthRequest> {
    override fun fromEntity(entity: UsersEntity): UsersResponse {
        return UsersResponse(
            entity.id,
            entity.name,
            entity.email,
            entity.authorities,
            entity.createAt,
            entity.updateAt,
        )
    }

    override fun toEntity(domain: UsersResponse): UsersEntity {
        return UsersEntity(
            null,
            domain.name,
            domain.email,
            "domain.password",
            domain.uAuthorities
        )
    }

    override fun toEntityT(domain: AuthRequest): UsersEntity {
        TODO("Not yet implemented")
    }
}