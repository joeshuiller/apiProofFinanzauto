package com.finanzauto.asisya.janes.proof.data.repository

import com.finanzauto.asisya.janes.proof.application.request.ProductRequest
import com.finanzauto.asisya.janes.proof.application.response.ProductResponse
import com.finanzauto.asisya.janes.proof.data.crud.CrudProduct
import com.finanzauto.asisya.janes.proof.data.mapper.ProductMapperService
import com.finanzauto.asisya.janes.proof.domain.exception.NotFoundException
import com.finanzauto.asisya.janes.proof.domain.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(
    private val product: CrudProduct,
    private val productMapper: ProductMapperService
): ProductRepository {
    override fun findAll(): List<ProductResponse?> {
        return product.findAll().map { productMapper.fromEntity(it) }
    }

    override fun finById(id: Long): ProductResponse? {
        val entity = product.findByIdOrNull(id)
            ?: throw NotFoundException("La producto con ID $id no existe")
        return productMapper.fromEntity(entity)
    }

    override fun save(request: ProductRequest): Long {
        val mapper = productMapper.toEntityT(request)
        val savedCategory = product.save(mapper)
        return savedCategory.id ?: throw IllegalStateException("Error al generar el ID de la producto")
    }

    override fun update(
        id: Long,
        request: ProductRequest
    ): ProductResponse {
        if (!product.existsById(id)) {
            throw NotFoundException("El producto con ID $id no existe")
        }
        val mapper = productMapper.toEntityT(request)
        return productMapper.fromEntity(product.save(mapper))
    }

    override fun delete(id: Long): Long {
        if (!product.existsById(id)) {
            throw NotFoundException("El producto con ID $id no existe")
        }
        product.deleteById(id)
        return id
    }
}