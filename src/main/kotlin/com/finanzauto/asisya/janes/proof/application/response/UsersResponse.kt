package com.finanzauto.asisya.janes.proof.application.response

import org.springframework.security.core.GrantedAuthority
import java.time.LocalDateTime

data class UsersResponse(
    val id: Long?,
    val name: String,
    val email: String,
    val uAuthorities: MutableCollection<GrantedAuthority>,
    val createAt: LocalDateTime? = null,
    val updateAt: LocalDateTime? = null,
)