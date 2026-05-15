package com.finanzauto.asisya.janes.proof.domain.service

import com.finanzauto.asisya.janes.proof.data.crud.CrudAuth
import com.finanzauto.asisya.janes.proof.data.entity.UsersEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserDetailsService(
    private  val  crudAuth: CrudAuth,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        // Create a method in your repo to find a user by its username
        val user = crudAuth.findByEmail(username)

        return UsersEntity(
            user.id,
            user.name,
            user.email,
            user.password,
            Collections.singleton(SimpleGrantedAuthority("user"))
        )
    }
}
