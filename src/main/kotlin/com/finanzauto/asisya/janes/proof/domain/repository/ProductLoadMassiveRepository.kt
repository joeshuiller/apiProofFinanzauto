package com.finanzauto.asisya.janes.proof.domain.repository

import com.finanzauto.asisya.janes.proof.application.request.ProductRequest

interface ProductLoadMassiveRepository {
    fun saveAllBulk(products: List<ProductRequest>)
}