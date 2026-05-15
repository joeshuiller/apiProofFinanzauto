package com.finanzauto.asisya.janes.proof.domain.service

import com.finanzauto.asisya.janes.proof.application.request.ProductRequest
import com.finanzauto.asisya.janes.proof.application.use_case.LoadMassiveProductsUseCase
import com.finanzauto.asisya.janes.proof.domain.repository.ProductLoadMassiveRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductBulkLoadService(
    private val productLoadMassiveRepository: ProductLoadMassiveRepository
) : LoadMassiveProductsUseCase {

    @Transactional
    override fun generateAndLoad(products: List<ProductRequest>) {
        // Delegamos al puerto de salida para la inserción ultra-rápida
        productLoadMassiveRepository.saveAllBulk(products)
    }
}