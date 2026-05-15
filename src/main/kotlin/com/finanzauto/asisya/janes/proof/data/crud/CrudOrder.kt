package com.finanzauto.asisya.janes.proof.data.crud

import com.finanzauto.asisya.janes.proof.data.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CrudOrder : JpaRepository<OrderEntity, Long>