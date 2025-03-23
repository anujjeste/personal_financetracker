package com.finance.tracker.services

import com.finance.tracker.models.User
import com.finance.tracker.repositories.UserRepository
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@Lazy
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    fun registerUser(username: String, password: String): User {
        val hashedPassword = passwordEncoder.encode(password)
        return userRepository.save(User(username = username, password = hashedPassword))
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found: $username")

        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            emptyList()
        )
    }
}
