package com.finanzauto.asisya.janes.proof.domain.repository

import com.finanzauto.asisya.janes.proof.application.request.CategoryRequest
import com.finanzauto.asisya.janes.proof.application.response.CategoryResponse

interface CategoryRepository {
    fun findAll(): List<CategoryResponse?>
    fun finById(id: Long): CategoryResponse?
    fun save(request: CategoryRequest): Long
    fun update(id: Long, request: CategoryRequest): CategoryResponse
    fun delete(id: Long): Long
}