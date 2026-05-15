package com.finanzauto.asisya.janes.proof.application.use_case

import com.finanzauto.asisya.janes.proof.application.request.ProductRequest
import com.finanzauto.asisya.janes.proof.application.request.SupplierRequest
import com.finanzauto.asisya.janes.proof.application.response.ProductResponse
import com.finanzauto.asisya.janes.proof.application.response.SupplierResponse

interface ProductUseCase {
    fun findAll(): List<ProductResponse?>
    fun finById(id: Long): ProductResponse?
    fun save(request: ProductRequest): Long
    fun update(id: Long, request: ProductRequest): ProductResponse
    fun delete(id: Long): Long
}