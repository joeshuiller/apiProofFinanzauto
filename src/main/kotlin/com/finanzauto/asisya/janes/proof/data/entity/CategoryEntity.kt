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
@Table(name = "Categories")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    var id: Long? = null,

    @Column(name = "CategoryName", nullable = false, length = 15)
    var categoryName: String,

    @Column(name = "Description", columnDefinition = "TEXT")
    var description: String? = null,

    @CreationTimestamp
    @Column(name = "create_at",nullable = true)
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "update_at",nullable = true)
    var updateAt: LocalDateTime? = null
)
