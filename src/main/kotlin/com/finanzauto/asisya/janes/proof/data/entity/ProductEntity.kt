package com.finanzauto.asisya.janes.proof.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "Products")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    var id: Long? = null,

    @Column(name = "ProductName", nullable = false, length = 40)
    var productName: String,

    // FK1: Relación con Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    var category: CategoryEntity?,

    // FK2: Relación con Supplier
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SupplierID", referencedColumnName = "SupplierID")
    var supplier: SupplierEntity?,

    @Column(name = "UnitPrice", precision = 10, scale = 4)
    var unitPrice: BigDecimal,

    @Column(name = "QuantityPerUnit")
    var quantityPerUnit: String?,

    @Column(name = "UnitsInStock")
    var unitsInStock: Int,

    @Column(name = "UnitsOnOrder")
    var unitsOnOrder: Int,

    @Column(name = "ReorderLevel")
    var reorderLevel: Int,

    @Column(name = "Discontinued", nullable = false)
    var discontinued: Boolean = false,

    @CreationTimestamp
    @Column(name = "create_at",nullable = true)
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "update_at",nullable = true)
    var updateAt: LocalDateTime? = null
)
