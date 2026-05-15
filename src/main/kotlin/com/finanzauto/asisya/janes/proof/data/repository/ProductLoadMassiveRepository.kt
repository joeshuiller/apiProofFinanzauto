package com.finanzauto.asisya.janes.proof.data.repository

import com.finanzauto.asisya.janes.proof.application.request.ProductRequest
import com.finanzauto.asisya.janes.proof.domain.repository.ProductLoadMassiveRepository
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement
import java.sql.Types

@Repository
class ProductLoadMassiveRepository(
    private val jdbcTemplate: JdbcTemplate
): ProductLoadMassiveRepository {
    override fun saveAllBulk(products: List<ProductRequest>) {
        val sql = """
            INSERT INTO Products (
                ProductName, 
                SupplierID, 
                CategoryID, 
                QuantityPerUnit, 
                UnitPrice, 
                UnitsInStock, 
                UnitsOnOrder, 
                ReorderLevel, 
                Discontinued
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        // Procesamos por lotes de 5000 para no ahogar la memoria
        val batchSize = 5000

        for (i in products.indices step batchSize) {
            val batchList = products.subList(i, minOf(i + batchSize, products.size))

            jdbcTemplate.batchUpdate(sql, object : BatchPreparedStatementSetter {
                override fun setValues(ps: PreparedStatement, j: Int) {
                    val product = batchList[j]
                    ps.setString(1, product.productName)
                    if (product.supplierId != null) {
                        ps.setLong(2, product.supplierId)
                    } else {
                        ps.setNull(2, Types.BIGINT)
                    }
                    ps.setLong(3, product.categoryId)
                    if (product.quantityPerUnit != null) {
                        ps.setString(4, product.quantityPerUnit)
                    } else {
                        ps.setNull(4, Types.VARCHAR)
                    }
                    ps.setBigDecimal(5, product.unitPrice)
                    ps.setInt(6, product.unitsInStock)
                    ps.setInt(7, product.unitsOnOrder)
                    ps.setInt(8, product.reorderLevel)
                    ps.setBoolean(9, product.discontinued)
                }

                override fun getBatchSize(): Int {
                    return batchList.size
                }
            })
        }
    }
}