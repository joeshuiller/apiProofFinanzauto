package com.finanzauto.asisya.janes.proof.application.use_case

import com.finanzauto.asisya.janes.proof.application.request.ProductRequest

interface LoadMassiveProductsUseCase {
    fun generateAndLoad(products: List<ProductRequest>)
}