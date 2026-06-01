package com.focos_dengue

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
import com.focos_dengue.data.remote.model.ScreenName
import com.focos_dengue.ui.auth_screen.login.LoginScreen
import com.focos_dengue.ui.auth_screen.signup.SignUpScreen
import com.focos_dengue.ui.report_screen.ReportScreen
import com.focos_dengue.ui.theme.FocosDengueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocosDengueTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController, ScreenName.REPORT.route) {
                        composable(route = ScreenName.LOGIN.route) {
                            LoginScreen { navController.navigate(ScreenName.SIGNUP.route) }
                        }
                        composable(route = ScreenName.SIGNUP.route) {
                            SignUpScreen { navController.navigate(ScreenName.LOGIN.route) }
                        }
                        composable(route = ScreenName.REPORT.route) {
                            ReportScreen()
                        }
                    }
                }
            }
        }
    }
}
