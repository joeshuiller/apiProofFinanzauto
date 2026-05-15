package com.finanzauto.asisya.janes.proof.application.use_case

import com.finanzauto.asisya.janes.proof.application.request.SupplierRequest
import com.finanzauto.asisya.janes.proof.application.response.SupplierResponse

interface SupplierUseCase {
    fun findAll(): List<SupplierResponse?>
    fun finById(id: Long): SupplierResponse?
    fun save(request: SupplierRequest): Long
    fun update(id: Long, request: SupplierRequest): SupplierResponse
    fun delete(id: Long): Long
}