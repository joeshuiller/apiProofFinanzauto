package com.finanzauto.asisya.janes.proof.data.mapper

interface Mapper<D, E, T> {
    fun fromEntity(entity: E): D
    fun toEntity(domain: D): E
    fun toEntityT(domain: T): E
}