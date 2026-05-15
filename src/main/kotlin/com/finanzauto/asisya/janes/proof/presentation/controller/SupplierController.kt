package com.finanzauto.asisya.janes.proof.presentation.controller

import com.finanzauto.asisya.janes.proof.application.request.SupplierRequest
import com.finanzauto.asisya.janes.proof.application.response.SupplierResponse
import com.finanzauto.asisya.janes.proof.application.use_case.SupplierUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/suppliers")
@Tag(name = "Proveedores", description = "Operaciones CRUD para la gestión de proveedores (Suppliers)")
class SupplierController(
    private val supplierService: SupplierUseCase
) {

    @Operation(summary = "Obtener todos los proveedores")
    @GetMapping
    fun getAllSuppliers(): ResponseEntity<List<SupplierResponse?>> {
        val suppliers = supplierService.findAll()
        return ResponseEntity.ok(suppliers)
    }

    @Operation(summary = "Obtener un proveedor por su ID")
    @GetMapping("/{id}")
    fun getSupplierById(@PathVariable id: Long): ResponseEntity<SupplierResponse> {
        val supplier = supplierService.finById(id)
        return ResponseEntity.ok(supplier)
    }

    @Operation(summary = "Crear un nuevo proveedor")
    @PostMapping
    fun createSupplier(
        @Valid @RequestBody request: SupplierRequest
    ): ResponseEntity<Long> {
        val createdSupplier = supplierService.save(request)
        // Retornamos 201 Created indicando que el recurso fue insertado exitosamente
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier)
    }

    @Operation(summary = "Actualizar un proveedor existente")
    @PutMapping("/{id}")
    fun updateSupplier(
        @PathVariable id: Long,
        @Valid @RequestBody request: SupplierRequest
    ): ResponseEntity<SupplierResponse> {
        val updatedSupplier = supplierService.update(id, request)
        return ResponseEntity.ok(updatedSupplier)
    }

    @Operation(summary = "Eliminar un proveedor")
    @DeleteMapping("/{id}")
    fun deleteSupplier(@PathVariable id: Long): ResponseEntity<Void> {
        supplierService.delete(id)
        // Retornamos 204 No Content indicando éxito sin enviar cuerpo de respuesta
        return ResponseEntity.noContent().build()
    }
}