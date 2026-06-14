package com.foco_acessibilidade_dengue

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
import com.foco_acessibilidade_dengue.ui.auth_screen.login.LoginScreen
import com.foco_acessibilidade_dengue.ui.auth_screen.signup.SignUpScreen
import com.foco_acessibilidade_dengue.ui.theme.FocoAcessibilidadeDengueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocoAcessibilidadeDengueTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController, "login") {
                        composable(route = "login") {
                            LoginScreen { navController.navigate("signup") }
                        }
                        composable(route = "signup") {
                            SignUpScreen { navController.navigate("login") }
                        }
                    }
                }
            }
        }
    }
}
