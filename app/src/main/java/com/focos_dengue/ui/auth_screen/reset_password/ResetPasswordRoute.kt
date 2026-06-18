package com.focos_dengue.ui.auth_screen.reset_password

import android.content.Intent
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.BundleCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.focos_dengue.FocosDengueApplication
import com.focos_dengue.ui.navigation.ScreenName

fun NavGraphBuilder.resetPasswordRoute(navController: NavController) {
    composable(
        route = ScreenName.RESET_PASSWORD.route,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "focosdengue://${ScreenName.RESET_PASSWORD.route}"
            }
        )
    ) { backStackEntry ->
        val app = LocalContext.current.applicationContext as FocosDengueApplication

        val intent = backStackEntry.arguments?.let { bundle ->
            BundleCompat.getParcelable(bundle, NavController.KEY_DEEP_LINK_INTENT, Intent::class.java)
        }

        val intentData = intent?.data
        val fragment = intentData?.fragment
        val arr = fragment?.split("&")
        val accessToken = arr?.find { it.startsWith("access_token=") }?.substringAfter("=")
        val refreshToken = arr?.find { it.startsWith("refresh_token=") }?.substringAfter("=")

        if (accessToken == null || refreshToken == null) {
            navController.navigate(ScreenName.LOGIN.route)
            Toast.makeText(LocalContext.current, "Erro ao recuperar senha. Tente novamente.", Toast.LENGTH_LONG).show()
            return@composable
        }

        ResetPasswordScreen(
            toLoginScreen = { navController.navigate(ScreenName.LOGIN.route) },
            viewModel = viewModel(
                factory = ResetPasswordViewModelFactory(
                    authRepository = app.container.authRepository,
                    accessToken = accessToken,
                    refreshToken = refreshToken
                )
            )
        )
    }
}
