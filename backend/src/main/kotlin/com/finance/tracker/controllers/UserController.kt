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

    // Allow requests from React frontend at localhost:3000
    @CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping("/register")
    fun registerUser(@RequestBody user: User): ResponseEntity<User> {
        val savedUser = userService.registerUser(user.username, user.password)
        return ResponseEntity.ok(savedUser)
    }

    @CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping("/login")
    fun loginUser(
        @RequestParam username: String,
        @RequestParam password: String
    ): ResponseEntity<Any> {
        return try {
            val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(username, password)
            )
            val token = jwtUtil.generateToken(authentication.principal as org.springframework.security.core.userdetails.User)
            ResponseEntity.ok(mapOf("token" to token))
        } catch (ex: Exception) {
            ResponseEntity.status(403).body(mapOf("error" to "Login failed: ${ex.message}"))
        }
    }
}
