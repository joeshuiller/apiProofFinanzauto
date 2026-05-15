package com.finanzauto.asisya.janes.proof.data.crud

import com.finanzauto.asisya.janes.proof.data.entity.OrderDetailEntity
import com.finanzauto.asisya.janes.proof.data.entity.OrderDetailId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CrudOrderDetail : JpaRepository<OrderDetailEntity, OrderDetailId>