package com.finanzauto.asisya.janes.proof.domain.service

import com.finanzauto.asisya.janes.proof.application.request.SupplierRequest
import com.finanzauto.asisya.janes.proof.application.response.SupplierResponse
import com.finanzauto.asisya.janes.proof.application.use_case.SupplierUseCase
import com.finanzauto.asisya.janes.proof.domain.repository.SupplierRepository
import org.springframework.stereotype.Service

@Service
class SupplierService(
    private val supplier: SupplierRepository,
): SupplierUseCase {
    override fun findAll(): List<SupplierResponse?> {
        return supplier.findAll()
    }

    override fun finById(id: Long): SupplierResponse? {
        return supplier.finById(id)
    }

    override fun save(request: SupplierRequest): Long {
        return supplier.save(request)
    }

    override fun update(
        id: Long,
        request: SupplierRequest
    ): SupplierResponse {
        return supplier.update(id, request)
    }

    override fun delete(id: Long): Long {
        return supplier.delete(id)
    }
}