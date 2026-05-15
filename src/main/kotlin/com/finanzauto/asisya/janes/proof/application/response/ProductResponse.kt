package com.finanzauto.asisya.janes.proof.application.response

import java.math.BigDecimal
import java.time.LocalDateTime

data class ProductResponse(
    val id: Long?,
    val productName: String,
    val categoryId: Long,
    val supplierId: Long,
    val quantityPerUnit: String?,
    val unitPrice: BigDecimal,
    val unitsInStock: Int,
    val unitsOnOrder: Int,
    val reorderLevel: Int,
    val discontinued: Boolean,
    val createAt: LocalDateTime? = null,
    val updateAt: LocalDateTime? = null,
)
