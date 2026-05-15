package com.finanzauto.asisya.janes.proof.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "Employees")
data class EmployeeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    var id: Long? = null,

    @Column(name = "LastName", nullable = false, length = 20)
    var lastName: String,

    @Column(name = "FirstName", nullable = false, length = 10)
    var firstName: String,

    @Column(name = "Title", length = 30)
    var title: String? = null,

    @Column(name = "TitleOfCourtesy", length = 25)
    var titleOfCourtesy: String? = null,

    @Column(name = "BirthDate")
    var birthDate: LocalDate? = null,

    @Column(name = "HireDate")
    var hireDate: LocalDate? = null,

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

    @Column(name = "HomePhone", length = 24)
    var homePhone: String? = null,

    @Column(name = "Extension", length = 4)
    var extension: String? = null,

    @Lob
    @Column(name = "Photo")
    var photo: ByteArray? = null,

    @Column(name = "Notes", columnDefinition = "TEXT")
    var notes: String? = null,

    // Relación consigo misma (Self-referencing)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReportsTo")
    var reportsTo: EmployeeEntity? = null,

    @CreationTimestamp
    @Column(name = "create_at",nullable = true)
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "update_at",nullable = true)
    var updateAt: LocalDateTime? = null
)