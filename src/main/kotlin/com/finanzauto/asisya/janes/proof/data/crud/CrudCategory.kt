package com.finanzauto.asisya.janes.proof.data.crud

import com.finanzauto.asisya.janes.proof.data.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CrudCategory : JpaRepository<CategoryEntity, Long>