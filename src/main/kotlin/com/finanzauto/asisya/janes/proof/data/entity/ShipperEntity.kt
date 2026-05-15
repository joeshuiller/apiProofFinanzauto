package com.finanzauto.asisya.janes.proof.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "Shippers")
data class ShipperEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShipperID")
    var id: Long? = null,

    @Column(name = "CompanyName", nullable = false, length = 40)
    var companyName: String,

    @Column(name = "Phone", length = 24)
    var phone: String? = null,

    @CreationTimestamp
    @Column(name = "create_at",nullable = true)
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "update_at",nullable = true)
    var updateAt: LocalDateTime? = null
)