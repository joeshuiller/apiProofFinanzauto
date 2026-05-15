package com.finanzauto.asisya.janes.proof.application.response

data class TokenAuthResponse(
    val token: String?,
    val tokenType: String?,
    val expiration: Long?
)