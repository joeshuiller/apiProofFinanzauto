package com.finanzauto.asisya.janes.proof.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "Customers")
data class CustomerEntity(
    @Id
    @Column(name = "CustomerID", length = 5, nullable = false)
    var id: Long,

    @Column(name = "CompanyName", nullable = false, length = 40)
    var companyName: String,

    @Column(name = "ContactName", length = 30)
    var contactName: String? = null,

    @Column(name = "ContactTitle", length = 30)
    var contactTitle: String? = null,

    @Column(name = "Address", length = 60)
    var address: String? = null,

    @Column(name = "City", length = 15)
    var city: String? = null,

    @Column(name = "Region", length = 15)
    var region: String? = null,

    @Column(name = "PostalCode", length = 10)
    var postalCode: String? = null,

    @Column(name = "Country", length = 15)
    var country: String? = null,

    @Column(name = "Phone", length = 24)
    var phone: String? = null,

    @Column(name = "Fax", length = 24)
    var fax: String? = null,

    @CreationTimestamp
    @Column(name = "create_at",nullable = true)
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "update_at",nullable = true)
    var updateAt: LocalDateTime? = null
)
