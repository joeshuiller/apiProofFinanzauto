package com.finanzauto.asisya.janes.proof.configuration

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService {

    // 💡 Tip de Senior: En un entorno real, estos valores no deben estar quemados en el código.
    // Deberían venir del archivo application.yml usando @Value("\${jwt.secret}")
    private val secret: String = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b"
    private val expiration: Long = 86400000 // 1 día

    fun getExpirationTime(): Long = expiration

    // 1. JJWT 0.12+ requiere que convirtamos los bytes en un objeto SecretKey
    private fun getSignInKey(): SecretKey {
        return Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun generateToken(username: String): String {
        val now = System.currentTimeMillis()

        return Jwts.builder()
            .subject(username) // 👈 'setSubject' cambió a 'subject'
            .issuedAt(Date(now))
            .expiration(Date(now + expiration)) // 👈 'setExpiration' cambió a 'expiration'
            // 👈 'signWith' ahora solo pide la llave. La librería deduce automáticamente el algoritmo (HS512/HS256) por el tamaño de tu llave.
            .signWith(getSignInKey())
            .compact()
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(getSignInKey()) // 👈 Cambio 1 (Reemplaza a setSigningKey)
            .build()
            .parseSignedClaims(token) // 👈 Cambio 2 (Reemplaza a parseClaimsJws)
            .payload // 👈 Cambio 3 (Reemplaza a body)
    }

    fun getEmail(token: String): String {
        return getClaims(token).subject
    }

    // 2. Quitamos el @ExceptionHandler y usamos lógica tradicional
    fun isTokenValid(token: String): Boolean {
        return try {
            val claims = getClaims(token)
            val expirationDate = claims.expiration
            val now = Date()

            // Si la fecha actual es ANTES de la fecha de expiración, es válido (true)
            now.before(expirationDate)

        } catch (e: ExpiredJwtException) {
            // Si la librería detecta que expiró, lanza este error. Lo atrapamos y decimos que es inválido.
            false
        } catch (e: Exception) {
            // Si el token está malformado, fue alterado, o la firma no coincide
            false
        }
    }
}