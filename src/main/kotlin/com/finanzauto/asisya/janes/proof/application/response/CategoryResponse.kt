package com.finanzauto.asisya.janes.proof.application.response

import java.time.LocalDateTime

data class CategoryResponse(
    val id: Long?,
    val categoryName: String,
    val description: String?,
    val createAt: LocalDateTime? = null,
    val updateAt: LocalDateTime? = null,
)