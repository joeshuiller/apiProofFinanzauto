package com.finanzauto.asisya.janes.proof.application.request

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class ProductRequest(
    @field:NotBlank(message = "El nombre del producto no puede estar vacío")
    val productName: String,

    @field:NotNull(message = "El ID de la categoría es obligatorio")
    val categoryId: Long,

    // FK2: Opcional
    val supplierId: Long? = null,

    // Descripción de la cantidad (ej. "10 boxes x 20 bags"). Opcional en Northwind.
    val quantityPerUnit: String? = null,

    @field:NotNull(message = "El precio es obligatorio")
    @field:DecimalMin(value = "0.0", inclusive = true, message = "El precio no puede ser negativo")
    val unitPrice: BigDecimal,

    @field:NotNull(message = "El stock es obligatorio")
    @field:Min(value = 0, message = "El stock no puede ser negativo")
    val unitsInStock: Int,

    // Nuevos campos agregados según el diagrama ER:

    @field:Min(value = 0, message = "Las unidades en orden no pueden ser negativas")
    val unitsOnOrder: Int = 0,

    @field:Min(value = 0, message = "El nivel de reorden no puede ser negativo")
    val reorderLevel: Int = 0,

    // Por defecto, un producto nuevo no está descontinuado
    val discontinued: Boolean = false
)
