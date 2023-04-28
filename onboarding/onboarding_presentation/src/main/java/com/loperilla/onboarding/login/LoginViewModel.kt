package com.loperilla.onboarding.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loperilla.model.auth.AuthResult
import com.loperilla.onboarding.login.model.LoginState
import com.loperilla.onboarding.utils.InputValidators
import com.loperilla.onboarding_domain.usecase.DoLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/*****
 * Project: CompraCasa
 * From: com.loperilla.compracasa.login
 * Created By Manuel Lopera on 21/4/23 at 18:02
 * All rights reserved 2023
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val doLoginUseCase: DoLoginUseCase
) : ViewModel() {

    private var _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private var _loginRequestState = MutableStateFlow<AuthResult>(AuthResult.AuthNone)
    val loginRequestState: StateFlow<AuthResult> = _loginRequestState

    fun loginDataChange(newEmailValue: String, newPasswordValue: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.update {
                it.copy(
                    emailValue = newEmailValue,
                    passwordValue = newPasswordValue,
                    bothValueValid = InputValidators.isEmailValid(newEmailValue) && InputValidators.isPasswordValid(
                        newPasswordValue
                    ),
                    requestFail = false
                )
            }
        }
    }

    fun loginButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            _loginRequestState.update {
                AuthResult.LoadingRequest
            }
            when (val result = doLoginUseCase(
                _loginState.value.emailValue,
                _loginState.value.passwordValue
            )) {
                is AuthResult.AuthFail -> {
                    _loginRequestState.update {
                        result
                    }
                    requestFailed()
                }

                AuthResult.AuthNone -> {

                }

                is AuthResult.AuthSuccess -> {
                    _loginRequestState.update {
                        result
                    }
                }

                AuthResult.LoadingRequest -> {
                    _loginState.update {
                        it.copy(
                            requestLaunched = true
                        )
                    }
                }
            }
        }
    }

    private fun requestFailed() {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.update {
                it.copy(
                    requestFail = true
                )
            }
        }
    }
}
