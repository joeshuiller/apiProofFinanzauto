package com.finanzauto.asisya.janes.proof.data.repository

import com.finanzauto.asisya.janes.proof.application.request.CategoryRequest
import com.finanzauto.asisya.janes.proof.application.response.CategoryResponse
import com.finanzauto.asisya.janes.proof.data.crud.CrudCategory
import com.finanzauto.asisya.janes.proof.data.mapper.CategoryMapperService
import com.finanzauto.asisya.janes.proof.domain.exception.NotFoundException
import com.finanzauto.asisya.janes.proof.domain.repository.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional


class CategoryRepository(
    private val category: CrudCategory,
    private val categoryMapper: CategoryMapperService
): CategoryRepository {
    override fun findAll(): List<CategoryResponse?> {
        return category.findAll().map { categoryMapper.fromEntity(it) }
    }

    override fun finById(id: Long): CategoryResponse? {
        val entity = category.findByIdOrNull(id)
            ?: throw NotFoundException("La categoría con ID $id no existe")
        return categoryMapper.fromEntity(entity)
    }

    @Transactional
    override fun save(request: CategoryRequest): Long {
        val mapper = categoryMapper.toEntityT(request)
        val savedCategory = category.save(mapper)
        return savedCategory.id ?: throw IllegalStateException("Error al generar el ID de la categoría")
    }

    @Transactional
    override fun update(
        id: Long,
        request: CategoryRequest
    ): CategoryResponse {
        if (!category.existsById(id)) {
            throw NotFoundException("El categoria con ID $id no existe")
        }
        val mapper = categoryMapper.toEntityT(request)
        return categoryMapper.fromEntity(category.save(mapper))
    }

    @Transactional
    override fun delete(id: Long): Long {
        if (!category.existsById(id)) {
            throw NotFoundException("El categoria con ID $id no existe")
        }
        category.deleteById(id)
        return id
    }
}