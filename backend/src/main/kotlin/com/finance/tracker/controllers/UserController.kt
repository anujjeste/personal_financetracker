package com.finance.tracker.controllers

import com.finance.tracker.models.User
import com.finance.tracker.security.JwtUtil
import com.finance.tracker.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
    private val jwtUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/register")
    fun registerUser(@RequestParam username: String, @RequestParam password: String): ResponseEntity<User> {
        val newUser = userService.registerUser(username, password)
        return ResponseEntity.ok(newUser)
    }

    @PostMapping("/login")
    fun loginUser(@RequestParam username: String, @RequestParam password: String): ResponseEntity<Map<String, String>> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password)
        )
        val token = jwtUtil.generateToken(authentication.principal as org.springframework.security.core.userdetails.User)
        return ResponseEntity.ok(mapOf("token" to token))
    }
}
