package com.finanzauto.asisya.janes.proof.domain.service

import com.finanzauto.asisya.janes.proof.application.request.ProductRequest
import com.finanzauto.asisya.janes.proof.application.response.ProductResponse
import com.finanzauto.asisya.janes.proof.application.use_case.ProductUseCase
import com.finanzauto.asisya.janes.proof.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val product: ProductRepository,
): ProductUseCase {
    override fun findAll(): List<ProductResponse?> {
        return product.findAll()
    }

    override fun finById(id: Long): ProductResponse? {
        return product.finById(id)
    }

    override fun save(request: ProductRequest): Long {
        return product.save(request)
    }

    override fun update(
        id: Long,
        request: ProductRequest
    ): ProductResponse {
        return product.update(id, request)
    }

    override fun delete(id: Long): Long {
        return product.delete(id)
    }
}