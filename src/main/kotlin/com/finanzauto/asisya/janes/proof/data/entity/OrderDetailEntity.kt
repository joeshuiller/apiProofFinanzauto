package com.finanzauto.asisya.janes.proof.data.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "Order_Details")
class OrderDetailEntity(

    @EmbeddedId
    var id: OrderDetailId,

    // Mapeo hacia la Orden usando la parte de la llave compuesta "orderId"
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "OrderID")
    var order: OrderEntity,

    // Mapeo hacia el Producto usando la parte de la llave compuesta "productId"
    // (Asegúrate de tener tu ProductEntity definido en este paquete)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "ProductID")
    var product: ProductEntity,

    @Column(name = "UnitPrice", nullable = false, precision = 10, scale = 4)
    var unitPrice: BigDecimal,

    @Column(name = "Quantity", nullable = false)
    var quantity: Int,

    @Column(name = "Discount", nullable = false, precision = 5, scale = 2)
    var discount: BigDecimal,

    @CreationTimestamp
    @Column(name = "create_at",nullable = true)
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "update_at",nullable = true)
    var updateAt: LocalDateTime? = null
)