package com.finanzauto.asisya.janes.proof.presentation.controller

import com.finanzauto.asisya.janes.proof.application.request.ProductRequest
import com.finanzauto.asisya.janes.proof.application.response.ProductResponse
import com.finanzauto.asisya.janes.proof.application.use_case.LoadMassiveProductsUseCase
import com.finanzauto.asisya.janes.proof.application.use_case.ProductUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Productos", description = "Operaciones CRUD para la gestión de productos")
class ProductController(
    private val productService: ProductUseCase,
    private val productLoadMassiveService: LoadMassiveProductsUseCase
) {

    @Operation(summary = "Obtener todos los productos")
    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductResponse?>> {
        val products = productService.findAll()
        return ResponseEntity.ok(products)
    }

    @Operation(summary = "Obtener un producto por su ID")
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        val product = productService.finById(id)
        return ResponseEntity.ok(product)
    }

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    fun createProduct(
        @Valid @RequestBody request: ProductRequest
    ): ResponseEntity<Long> {
        val createdProduct = productService.save(request)
        // 201 CREATED es el estándar estricto cuando se crea un recurso nuevo
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct)
    }

    @Operation(summary = "Crear un nuevo producto masivo")
    @PostMapping("/massiveProduct")
    fun createMassiveProduct(
        @Valid @RequestBody request: List<ProductRequest>
    ): ResponseEntity<Map<String, String>> {
        productLoadMassiveService.generateAndLoad(request)
        val responseBody = mapOf(
            "message" to "Operación exitosa. Se han generado e insertado ${request.count()} productos en la base de datos."
        )
        // 201 CREATED es el estándar estricto cuando se crea un recurso nuevo
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody)
    }

    @Operation(summary = "Crear un nuevo producto masivo automatico")
    @PostMapping("/massive")
    fun generateAndLoadAuto(
        @RequestParam(defaultValue = "100000") count: Int
    ): ResponseEntity<Map<String, String>> {
        productLoadMassiveService.generateAndLoadAuto(count)
        val responseBody = mapOf(
            "message" to "Operación exitosa. Se han generado e insertado $count productos en la base de datos."
        )
        // 201 CREATED es el estándar estricto cuando se crea un recurso nuevo
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody)
    }

    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @Valid @RequestBody request: ProductRequest
    ): ResponseEntity<ProductResponse> {
        val updatedProduct = productService.update(id, request)
        return ResponseEntity.ok(updatedProduct)
    }

    @Operation(summary = "Eliminar un producto")
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        productService.delete(id)
        // 204 NO CONTENT indica que la acción fue exitosa pero no hay JSON de respuesta
        return ResponseEntity.noContent().build()
    }
}