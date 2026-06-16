package com.focos_dengue.ui.auth_screen.signup

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.focos_dengue.FocosDengueApplication
import com.focos_dengue.ui.navigation.ScreenName

fun NavGraphBuilder.signUpRoute(navController: NavHostController) {
    composable(route = ScreenName.SIGNUP.route) {
        val app = LocalContext.current.applicationContext as FocosDengueApplication

        SignUpScreen(
            toLoginScreen = {
                navController.navigate(ScreenName.LOGIN.route)
            },
            viewModel = viewModel(factory = SignUpViewModelFactory(app.container.authRepository))
        )
    }
}
