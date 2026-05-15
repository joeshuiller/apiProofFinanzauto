package com.finanzauto.asisya.janes.proof.data.mapper

import com.finanzauto.asisya.janes.proof.application.request.CategoryRequest
import com.finanzauto.asisya.janes.proof.application.response.CategoryResponse
import com.finanzauto.asisya.janes.proof.data.entity.CategoryEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class CategoryMapperService: Mapper<CategoryResponse, CategoryEntity, CategoryRequest> {
    override fun fromEntity(entity: CategoryEntity): CategoryResponse {
        return CategoryResponse(
            entity.id,
            entity.categoryName,
            entity.description,
            entity.createAt,
            entity.updateAt
        )
    }

    override fun toEntity(domain: CategoryResponse): CategoryEntity {
        return CategoryEntity(
            null,
            domain.categoryName,
            domain.description,
        )
    }

    override fun toEntityT(domain: CategoryRequest): CategoryEntity {
        return CategoryEntity(
            null,
            domain.categoryName,
            domain.description,
        )
    }
}