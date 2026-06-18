package com.focos_dengue.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.focos_dengue.ui.auth_screen.login.loginRoute
import com.focos_dengue.ui.auth_screen.reset_password.resetPasswordRoute
import com.focos_dengue.ui.auth_screen.signup.signUpRoute
import com.focos_dengue.ui.main_screen.account_screen.accountRoute
import com.focos_dengue.ui.main_screen.report_screen.reportRoute
import com.focos_dengue.ui.main_screen.send_report_screen.sendReportRoute
import com.focos_dengue.ui.navigation.ScreenName
import com.focos_dengue.ui.theme.FocosDengueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocosDengueTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, ScreenName.LOGIN.route) {
                        loginRoute(navController)
                        signUpRoute(navController)
                        resetPasswordRoute(navController)
                        reportRoute(navController)
                        sendReportRoute(navController)
                        accountRoute(navController)
                    }
                }
            }
        }
    }
}