package com.finanzauto.asisya.janes.proof.application.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CategoryRequest(
    @field:NotBlank(message = "El nombre de la categoría es obligatorio")
    @field:Size(max = 15, message = "El nombre de la categoría no puede exceder los 15 caracteres")
    val categoryName: String,
    val description: String? = null,
)
