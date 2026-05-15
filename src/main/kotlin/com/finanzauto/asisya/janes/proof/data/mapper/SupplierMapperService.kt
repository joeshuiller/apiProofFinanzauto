package com.finanzauto.asisya.janes.proof.data.mapper

import com.finanzauto.asisya.janes.proof.application.request.SupplierRequest
import com.finanzauto.asisya.janes.proof.application.response.SupplierResponse
import com.finanzauto.asisya.janes.proof.data.entity.SupplierEntity

class SupplierMapperService  : Mapper<SupplierResponse, SupplierEntity, SupplierRequest> {
    override fun fromEntity(entity: SupplierEntity): SupplierResponse {
        return SupplierResponse(
            entity.id,
            entity.companyName,
            entity.contactName,
            entity.contactTitle,
            entity.address,
            entity.city,
            entity.region,
            entity.postalCode,
            entity.country,
            entity.phone,
            entity.fax,
            entity.homePage,
            entity.createAt,
            entity.updateAt
        )
    }

    override fun toEntity(domain: SupplierResponse): SupplierEntity {
        return SupplierEntity(
            null,
            domain.companyName,
            domain.contactName,
            domain.contactTitle,
            domain.address,
            domain.city,
            domain.region,
            domain.postalCode,
            domain.country,
            domain.phone,
            domain.fax,
            domain.homePage,
            domain.createAt,
            domain.updateAt
        )
    }

    override fun toEntityT(domain: SupplierRequest): SupplierEntity {
        return SupplierEntity(
            null,
            domain.companyName,
            domain.contactName,
            domain.contactTitle,
            domain.address,
            domain.city,
            domain.region,
            domain.postalCode,
            domain.country,
            domain.phone,
            domain.fax,
            domain.homePage,
        )
    }
}