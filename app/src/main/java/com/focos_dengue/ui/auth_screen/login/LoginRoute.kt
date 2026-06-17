package com.focos_dengue.ui.auth_screen.login

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.focos_dengue.FocosDengueApplication
import com.focos_dengue.ui.navigation.ScreenName
import com.focos_dengue.ui.util.LoadingBox

fun NavGraphBuilder.loginRoute(navController: NavHostController) {
    composable(
        route = ScreenName.LOGIN.route,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "focosdengue://${ScreenName.LOGIN.route}"
            }
        )
    ) {
        val app = LocalContext.current.applicationContext as FocosDengueApplication
        val authRepository = app.container.authRepository

        var isCheckingAuth by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            if (authRepository.isAuthenticated())
                navController.navigate(ScreenName.REPORT.route)
            else
                isCheckingAuth = false
        }

        if (!isCheckingAuth) {
            LoginScreen(
                toSignUpScreen = {
                    navController.navigate(ScreenName.SIGNUP.route)
                },
                toReportScreen = {
                    navController.navigate(ScreenName.REPORT.route)
                },
                viewModel = viewModel(factory = LoginViewModelFactory(authRepository))
            )
        }
        else {
            LoadingBox()
        }
    }
}
