package com.finanzauto.asisya.janes.proof.domain.dto

import org.springframework.security.core.GrantedAuthority

data class AuthDTO (
    val name: String,
    val email: String,
    val password: String,
    val uAuthorities: MutableCollection<GrantedAuthority>
)