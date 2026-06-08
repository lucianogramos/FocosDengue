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
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.focos_dengue.ui.auth_screen.forgot_password.ForgotPasswordScreen
import com.focos_dengue.ui.navigation.ScreenName
import com.focos_dengue.ui.auth_screen.login.LoginScreen
import com.focos_dengue.ui.auth_screen.signup.SignUpScreen
import com.focos_dengue.ui.main_screen.account_screen.AccountScreen
import com.focos_dengue.ui.main_screen.report_screen.ReportScreen
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
                        composable(route = ScreenName.LOGIN.route) {
                            LoginScreen(
                                toSignUpScreen = {
                                    navController.navigate(ScreenName.SIGNUP.route)
                                },
                                toForgotPasswordScreen = {
                                    navController.navigate(ScreenName.FORGOT_PASSWORD.route)
                                },
                                toReportScreen =  {
                                    navController.navigate(ScreenName.REPORT.route)
                                }
                            )
                        }
                        composable(route = ScreenName.SIGNUP.route) {
                            SignUpScreen(
                                toLoginScreen = {
                                    navController.navigate(ScreenName.LOGIN.route)
                                },
                                toReportScreen = {
                                    navController.navigate(ScreenName.REPORT.route)
                                }
                            )
                        }
                        composable(route = ScreenName.FORGOT_PASSWORD.route) {
                            ForgotPasswordScreen({ navController.navigate(ScreenName.LOGIN.route) })
                        }
                        composable(route = ScreenName.REPORT.route) {
                            ReportScreen({ navController.navigate(ScreenName.ACCOUNT.route) })
                        }
                        composable(route = ScreenName.ACCOUNT.route) {
                            AccountScreen({ navController.navigate(ScreenName.REPORT.route) })
                        }
                    }
                }
            }
        }
    }
}