package com.finanzauto.asisya.janes.proof.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

// Annotate with @ResponseStatus to automatically set the HTTP status code
@ResponseStatus(HttpStatus.UNAUTHORIZED)
class EmailNotExit(message: String) : RuntimeException(message) {
}