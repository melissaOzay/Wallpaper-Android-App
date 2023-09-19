package com.example.havucwallpapernewversion.features.account.domain.usecase

import com.example.havucwallpapernewversion.features.account.data.model.request.RegisterUserRequest
import com.example.havucwallpapernewversion.features.account.data.repository.AccountRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(userRequest: RegisterUserRequest) =
        accountRepository.registerUser(userRequest)
}