package com.finanzauto.asisya.janes.proof.domain.service

import com.finanzauto.asisya.janes.proof.application.request.CategoryRequest
import com.finanzauto.asisya.janes.proof.application.response.CategoryResponse
import com.finanzauto.asisya.janes.proof.application.use_case.CategoryUseCase
import com.finanzauto.asisya.janes.proof.domain.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val category: CategoryRepository,
): CategoryUseCase {
    override fun findAll(): List<CategoryResponse?> {
        return category.findAll()
    }

    override fun finById(id: Long): CategoryResponse? {
        return category.finById(id)
    }

    override fun save(request: CategoryRequest): Long {
        return category.save(request)
    }

    override fun update(
        id: Long,
        request: CategoryRequest
    ): CategoryResponse {
        return category.update(id, request)
    }

    override fun delete(id: Long): Long {
        return category.delete(id)
    }
}