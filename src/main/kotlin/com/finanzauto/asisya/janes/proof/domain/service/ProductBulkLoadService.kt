package com.finanzauto.asisya.janes.proof.domain.service

import com.finanzauto.asisya.janes.proof.application.request.ProductRequest
import com.finanzauto.asisya.janes.proof.application.use_case.LoadMassiveProductsUseCase
import com.finanzauto.asisya.janes.proof.domain.repository.ProductLoadMassiveRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

@Service
class ProductBulkLoadService(
    private val productLoadMassiveRepository: ProductLoadMassiveRepository
) : LoadMassiveProductsUseCase {

    @Transactional
    override fun generateAndLoad(products: List<ProductRequest>) {
        // Delegamos al puerto de salida para la inserción ultra-rápida
        productLoadMassiveRepository.saveAllBulk(products)
    }

    @Transactional
    override fun generateAndLoadAuto(count: Int) { // Cambiamos List por Int
        // 1. Usamos un nombre diferente para la lista local para evitar colisiones
        val domainProducts = ArrayList<ProductRequest>(count)

        val targetCategoryIds = listOf(1L, 2L)

        for (i in 1..count) {
            val randomCategory = targetCategoryIds.random()
            val randomPrice = BigDecimal(Random.nextDouble(10.0, 5000.0)).setScale(2, RoundingMode.HALF_UP)
            val randomStock = Random.nextInt(0, 1000)

            domainProducts.add(
                ProductRequest(
                    productName = "Tech Product $i-${Random.nextInt(1000, 9999)}",
                    categoryId = 1, // Usamos el Long directamente
                    supplierId = null, // Opcional
                    quantityPerUnit = "1 unit",
                    unitPrice = randomPrice,
                    unitsInStock = randomStock,
                    unitsOnOrder = 0,
                    reorderLevel = 10,
                    discontinued = false
                )
            )
        }

        // 2. Enviamos la lista procesada al puerto
        productLoadMassiveRepository.saveAllBulk(domainProducts)
    }
}