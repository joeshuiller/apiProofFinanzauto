package com.finanzauto.asisya.janes.proof.data.crud

import com.finanzauto.asisya.janes.proof.data.entity.SupplierEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CrudSupplier : JpaRepository<SupplierEntity, Long>