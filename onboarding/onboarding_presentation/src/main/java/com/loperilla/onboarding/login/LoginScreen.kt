package com.loperilla.onboarding.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import com.loperilla.core_ui.LOW
import com.loperilla.core_ui.button.FormButton
import com.loperilla.core_ui.input.EmailInput
import com.loperilla.core_ui.input.PasswordInput
import com.loperilla.core_ui.routes.Routes.HOME
import com.loperilla.core_ui.util.UiNavEvent
import com.loperilla.model.auth.AuthResult
import com.loperilla.onboarding.login.model.LoginState

/*****
 * Project: CompraCasa
 * From: com.loperilla.compracasa.login
 * Created By Manuel Lopera on 21/4/23 at 17:52
 * All rights reserved 2023
 */

@Composable
fun LoginScreen(
    loginFormState: LoginState,
    requestState: AuthResult,
    modifier: Modifier,
    loginButtonClicked: () -> Unit,
    onDataChange: (String, String) -> Unit,
    onNavigate: (UiNavEvent.Navigate) -> Unit
) {
    when (requestState) {
        is AuthResult.AuthFail -> {
            LoginForm(loginFormState, modifier, loginButtonClicked, onDataChange)
        }

        AuthResult.AuthNone -> {
            LoginForm(loginFormState, modifier, loginButtonClicked, onDataChange)
        }

        is AuthResult.AuthSuccess -> {
            onNavigate(UiNavEvent.Navigate(HOME))
        }

        AuthResult.LoadingRequest -> {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

}

@Composable
fun LoginForm(
    state: LoginState,
    modifier: Modifier = Modifier,
    loginButtonClicked: () -> Unit,
    onDataChange: (String, String) -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (cardFailRequest, loginEmail, loginPassword, loginButton) = createRefs()
        if (state.requestFail) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .constrainAs(cardFailRequest) {
                        top.linkTo(parent.top, margin = LOW)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Login Fail",
                    modifier = Modifier
                        .padding(LOW)
                )
            }
        }
        LoginInputEmail(
            state,
            modifier = Modifier
                .constrainAs(loginEmail) {
                    top.linkTo(cardFailRequest.bottom, margin = LOW)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) { newEmailValue ->
            onDataChange(newEmailValue, state.passwordValue)
        }
        LoginInputPassword(
            state,
            modifier = Modifier
                .constrainAs(loginPassword) {
                    top.linkTo(loginEmail.bottom, margin = LOW)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) { newPasswordValue ->
            onDataChange(state.emailValue, newPasswordValue)
        }
        LoginButton(
            state,
            modifier = Modifier
                .constrainAs(loginButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            loginButtonClicked()
        }
    }
}

@Composable
fun LoginInputEmail(
    state: LoginState,
    modifier: Modifier,
    onValueChange: (String) -> Unit
) {
    EmailInput(
        modifier = modifier,
        inputValue = state.emailValue,
        onValueChange = {
            onValueChange(it)
        }
    )
}

@Composable
fun LoginInputPassword(
    state: LoginState,
    modifier: Modifier,
    onValueChange: (String) -> Unit
) {
    PasswordInput(
        modifier = modifier,
        inputValue = state.passwordValue,
        onValueChange = {
            onValueChange(it)
        }
    )
}

@Composable
fun LoginButton(
    state: LoginState,
    modifier: Modifier,
    onButtonClick: () -> Unit
) {
    FormButton(
        textButton = "Iniciar sesión",
        modifier = modifier,
        onClickButton = {
            onButtonClick()
        },
        enableButton = state.bothValueValid
    )
}
