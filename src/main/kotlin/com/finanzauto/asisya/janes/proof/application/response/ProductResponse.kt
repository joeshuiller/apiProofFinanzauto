package com.finanzauto.asisya.janes.proof.application.response

import java.math.BigDecimal

data class ProductResponse(
    val id: Long,
    val productName: String,
    val categoryId: Long,
    val supplierId: Long?,
    val quantityPerUnit: String?,
    val unitPrice: BigDecimal,
    val unitsInStock: Int,
    val unitsOnOrder: Int,
    val reorderLevel: Int,
    val discontinued: Boolean
)
