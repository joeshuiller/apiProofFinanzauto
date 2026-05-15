package com.finanzauto.asisya.janes.proof.data.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "Orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    var id: Long? = null,

    // FK1 hacia Customers
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID")
    var customer: CustomerEntity? = null,

    // FK2 hacia Employees
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmployeeID")
    var employee: EmployeeEntity? = null,

    @Column(name = "OrderDate")
    var orderDate: LocalDateTime? = null,

    @Column(name = "RequiredDate")
    var requiredDate: LocalDateTime? = null,

    @Column(name = "ShippedDate")
    var shippedDate: LocalDateTime? = null,

    // FK3 hacia Shippers
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ShipVia")
    var shipVia: ShipperEntity? = null,

    @Column(name = "Freight", precision = 10, scale = 4)
    var freight: BigDecimal? = null,

    @Column(name = "ShipName", length = 40)
    var shipName: String? = null,

    @Column(name = "ShipAddress", length = 60)
    var shipAddress: String? = null,

    @Column(name = "ShipCity", length = 15)
    var shipCity: String? = null,

    @Column(name = "ShipRegion", length = 15)
    var shipRegion: String? = null,

    @Column(name = "ShipPostalCode", length = 10)
    var shipPostalCode: String? = null,

    @Column(name = "ShipCountry", length = 15)
    var shipCountry: String? = null,

    // Relación Bidireccional hacia los detalles
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    var details: MutableList<OrderDetailEntity> = mutableListOf(),

    @CreationTimestamp
    @Column(name = "create_at",nullable = true)
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "update_at",nullable = true)
    var updateAt: LocalDateTime? = null
)
