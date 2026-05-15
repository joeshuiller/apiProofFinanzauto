package com.finanzauto.asisya.janes.proof.application.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AuthRequest(
    @NotNull
    @NotBlank
    val name: String,
    @NotNull
    @NotBlank
    val email: String,
    @NotNull
    @NotBlank
    val password: String
)
