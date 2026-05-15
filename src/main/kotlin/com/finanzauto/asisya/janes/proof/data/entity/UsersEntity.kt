package com.finanzauto.asisya.janes.proof.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Table(name = "users")
@Entity
data class UsersEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "name",nullable = false)
    val name: String,
    @Column(name = "email",unique=true,nullable = false)
    val email: String,
    @Column(name = "password",nullable = false)
    private val password: String,
    @Column(name = "u_authorities",nullable = true)
    private val uAuthorities: MutableCollection<GrantedAuthority>,
    @CreationTimestamp
    @Column(name = "create_at",nullable = true)
    var createAt: LocalDateTime? = null,
    @UpdateTimestamp
    @Column(name = "update_at",nullable = true)
    var updateAt: LocalDateTime? = null
) : UserDetails {
    override fun getAuthorities() = uAuthorities
    override fun getPassword() = password
    override fun getUsername() = email
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired()= true
    override fun isEnabled() = true
}