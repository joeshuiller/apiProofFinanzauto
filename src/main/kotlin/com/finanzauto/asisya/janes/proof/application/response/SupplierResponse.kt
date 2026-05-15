package com.finanzauto.asisya.janes.proof.application.response

import java.time.LocalDateTime


data class SupplierResponse(
    val id: Long?,
    val companyName: String,
    val contactName: String?,
    val contactTitle: String?,
    val address: String?,
    val city: String?,
    var region: String?,
    var postalCode: String?,
    val country: String?,
    val phone: String?,
    var fax: String?,
    var homePage: String?,
    val createAt: LocalDateTime? = null,
    val updateAt: LocalDateTime? = null,
)
