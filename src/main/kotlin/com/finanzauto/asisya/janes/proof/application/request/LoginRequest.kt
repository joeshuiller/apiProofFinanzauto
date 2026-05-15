package com.finanzauto.asisya.janes.proof.application.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class LoginRequest(
    @NotNull
    @NotBlank
    @JsonProperty("email")
    val email: String,
    @NotNull
    @NotBlank
    @JsonProperty("password")
    val password: String
)
