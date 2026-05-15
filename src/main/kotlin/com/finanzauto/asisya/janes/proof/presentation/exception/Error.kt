package com.finanzauto.asisya.janes.proof.presentation.exception

data class Error(
    val status: Int,
    val type: String,
    val message: String
)
