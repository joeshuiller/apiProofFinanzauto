package com.finanzauto.asisya.janes.proof.application.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SupplierRequest(

    @field:NotBlank(message = "El nombre de la empresa (Company Name) es obligatorio")
    @field:Size(max = 40, message = "El nombre de la empresa no puede exceder los 40 caracteres")
    val companyName: String,

    @field:Size(max = 30, message = "El nombre del contacto no puede exceder los 30 caracteres")
    val contactName: String? = null,

    @field:Size(max = 30, message = "El título del contacto no puede exceder los 30 caracteres")
    val contactTitle: String? = null,

    @field:Size(max = 60, message = "La dirección no puede exceder los 60 caracteres")
    val address: String? = null,

    @field:Size(max = 15, message = "La ciudad no puede exceder los 15 caracteres")
    val city: String? = null,

    // Nuevos campos agregados:

    @field:Size(max = 15, message = "La región no puede exceder los 15 caracteres")
    val region: String? = null,

    @field:Size(max = 10, message = "El código postal no puede exceder los 10 caracteres")
    val postalCode: String? = null,

    @field:Size(max = 15, message = "El país no puede exceder los 15 caracteres")
    val country: String? = null,

    @field:Size(max = 24, message = "El teléfono no puede exceder los 24 caracteres")
    val phone: String? = null,

    @field:Size(max = 24, message = "El fax no puede exceder los 24 caracteres")
    val fax: String? = null,

    // HomePage suele ser de tipo TEXT en la base de datos, por lo que podemos omitir el @Size o darle un límite amplio
    val homePage: String? = null
)