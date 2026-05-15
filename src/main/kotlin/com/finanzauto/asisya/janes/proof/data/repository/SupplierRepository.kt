package com.finanzauto.asisya.janes.proof.data.repository

import com.finanzauto.asisya.janes.proof.application.request.SupplierRequest
import com.finanzauto.asisya.janes.proof.application.response.SupplierResponse
import com.finanzauto.asisya.janes.proof.data.crud.CrudSupplier
import com.finanzauto.asisya.janes.proof.data.mapper.SupplierMapperService
import com.finanzauto.asisya.janes.proof.domain.exception.NotFoundException
import com.finanzauto.asisya.janes.proof.domain.repository.SupplierRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class SupplierRepository(
    private val supplier: CrudSupplier,
    private val supplierMapper: SupplierMapperService
): SupplierRepository {
    override fun findAll(): List<SupplierResponse?> {
        return supplier.findAll().map { supplierMapper.fromEntity(it) }
    }

    override fun finById(id: Long): SupplierResponse? {
        val entity = supplier.findByIdOrNull(id)
            ?: throw NotFoundException("La proveedor con ID $id no existe")
        return supplierMapper.fromEntity(entity)
    }

    override fun save(request: SupplierRequest): Long {
        val mapper = supplierMapper.toEntityT(request)
        val savedCategory = supplier.save(mapper)
        return savedCategory.id ?: throw IllegalStateException("Error al generar el ID de la proveedor")
    }

    override fun update(
        id: Long,
        request: SupplierRequest
    ): SupplierResponse {
        if (!supplier.existsById(id)) {
            throw NotFoundException("El categoria con ID $id no existe")
        }
        val mapper = supplierMapper.toEntityT(request)
        return supplierMapper.fromEntity(supplier.save(mapper))
    }

    override fun delete(id: Long): Long {
        if (!supplier.existsById(id)) {
            throw NotFoundException("El categoria con ID $id no existe")
        }
        supplier.deleteById(id)
        return id
    }
}