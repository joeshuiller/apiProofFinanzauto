package com.finanzauto.asisya.janes.proof.data.mapper

import com.finanzauto.asisya.janes.proof.application.request.ProductRequest
import com.finanzauto.asisya.janes.proof.application.response.ProductResponse
import com.finanzauto.asisya.janes.proof.data.crud.CrudCategory
import com.finanzauto.asisya.janes.proof.data.crud.CrudSupplier
import com.finanzauto.asisya.janes.proof.data.entity.ProductEntity
import com.finanzauto.asisya.janes.proof.domain.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ProductMapperService(
    private val category: CrudCategory,
    private val supplier: CrudSupplier,
) : Mapper<ProductResponse, ProductEntity, ProductRequest> {

    override fun fromEntity(entity: ProductEntity): ProductResponse {
        return ProductResponse(
            id = entity.id ?: 0L,
            productName = entity.productName,

            // 2. Quitamos los '!!' y usamos el operador Elvis o safe calls
            categoryId = entity.category?.id ?: 0L,
            supplierId = entity.supplier?.id ?: 0L,

            quantityPerUnit = entity.quantityPerUnit,
            unitPrice = entity.unitPrice,
            unitsInStock = entity.unitsInStock,
            unitsOnOrder = entity.unitsOnOrder,
            reorderLevel = entity.reorderLevel,
            discontinued = entity.discontinued,
            createAt = entity.createAt,
            updateAt = entity.updateAt
        )
    }

    override fun toEntity(domain: ProductResponse): ProductEntity {
        val categoryEntity = category.findByIdOrNull(domain.categoryId)
            ?: throw NotFoundException("La categoría con ID ${domain.categoryId} no existe")

        // 3. Corregimos el copy-paste: Usamos supplierId.
        // Como el proveedor suele ser opcional (nullable), usamos 'let'
        val supplierEntity = domain.supplierId?.let {
            supplier.findByIdOrNull(it) ?: throw NotFoundException("El proveedor con ID $it no existe")
        }

        return ProductEntity(
            id = domain.id, // 4. Pasamos el ID real para que no cree duplicados al actualizar
            productName = domain.productName,
            category = categoryEntity,
            supplier = supplierEntity,
            unitPrice = domain.unitPrice,
            quantityPerUnit = domain.quantityPerUnit,
            unitsInStock = domain.unitsInStock,
            unitsOnOrder = domain.unitsOnOrder,
            reorderLevel = domain.reorderLevel,
            discontinued = domain.discontinued,
        )
    }

    override fun toEntityT(domain: ProductRequest): ProductEntity {
        val categoryEntity = category.findByIdOrNull(domain.categoryId)
            ?: throw NotFoundException("La categoría con ID ${domain.categoryId} no existe")

        // Corregimos el copy-paste aquí también
        val supplierEntity = domain.supplierId?.let {
            supplier.findByIdOrNull(it) ?: throw NotFoundException("El proveedor con ID $it no existe")
        }

        return ProductEntity(
            id = null, // Aquí sí va null porque Request es para crear nuevos registros
            productName = domain.productName,
            category = categoryEntity,
            supplier = supplierEntity,
            unitPrice = domain.unitPrice,
            quantityPerUnit = domain.quantityPerUnit,
            unitsInStock = domain.unitsInStock,
            unitsOnOrder = domain.unitsOnOrder,
            reorderLevel = domain.reorderLevel,
            discontinued = domain.discontinued,
        )
    }
}