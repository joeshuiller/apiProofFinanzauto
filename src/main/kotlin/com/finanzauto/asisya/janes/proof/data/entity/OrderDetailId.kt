package com.finanzauto.asisya.janes.proof.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class OrderDetailId(
    @Column(name = "OrderID")
    var orderId: Long = 0,

    @Column(name = "ProductID")
    var productId: Long = 0
) : Serializable