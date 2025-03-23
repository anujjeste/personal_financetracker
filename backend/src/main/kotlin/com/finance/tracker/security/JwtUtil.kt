package com.finance.tracker.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import java.nio.charset.StandardCharsets
import javax.crypto.SecretKey

@Component
class JwtUtil {

    @Value("\${jwt.secret}")
    private lateinit var secretKey: String

    private fun getSigningKey(): SecretKey {
        return Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))
    }

    fun generateToken(userDetails: UserDetails): String {
        return Jwts.builder()
            .setSubject(userDetails.username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hrs
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun extractUsername(token: String): String {
        return extractClaim(token) { it.subject }
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token) { it.expiration }
    }

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
        return claimsResolver(claims)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }
}
