package com.finanzauto.asisya.janes.proof.presentation.controller

import com.finanzauto.asisya.janes.proof.application.request.CategoryRequest
import com.finanzauto.asisya.janes.proof.application.response.CategoryResponse
import com.finanzauto.asisya.janes.proof.application.use_case.CategoryUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categorías", description = "Operaciones CRUD para la gestión de categorías")
class CategoryController(
    private val categoryService: CategoryUseCase
) {

    @Operation(summary = "Obtener todas las categorías")
    @GetMapping
    fun getAllCategories(): ResponseEntity<List<CategoryResponse?>> {
        val categories = categoryService.findAll()
        return ResponseEntity.ok(categories)
    }

    @Operation(summary = "Obtener una categoría por su ID")
    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<CategoryResponse> {
        val category = categoryService.finById(id)
        return ResponseEntity.ok(category)
    }

    @Operation(summary = "Crear una nueva categoría")
    @PostMapping
    fun createCategory(
        @Valid @RequestBody request: CategoryRequest
    ): ResponseEntity<Long> {
        val createdCategory = categoryService.save(request)
        // 201 CREATED es el estándar REST cuando se inserta un nuevo recurso
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory)
    }

    @Operation(summary = "Actualizar una categoría existente")
    @PutMapping("/{id}")
    fun updateCategory(
        @PathVariable id: Long,
        @Valid @RequestBody request: CategoryRequest
    ): ResponseEntity<CategoryResponse> {
        val updatedCategory = categoryService.update(id, request)
        return ResponseEntity.ok(updatedCategory)
    }

    @Operation(summary = "Eliminar una categoría")
    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Void> {
        categoryService.delete(id)
        // 204 NO CONTENT es el estándar REST para borrados exitosos sin cuerpo de respuesta
        return ResponseEntity.noContent().build()
    }
}