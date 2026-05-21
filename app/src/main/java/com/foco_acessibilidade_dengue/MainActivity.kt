package com.foco_acessibilidade_dengue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.foco_acessibilidade_dengue.ui.login.LoginTopbar
import com.foco_acessibilidade_dengue.ui.login.LoginActivity
import com.foco_acessibilidade_dengue.ui.theme.FocoAcessibilidadeDengueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocoAcessibilidadeDengueTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            LoginTopbar()
                        }
                    ) { innerPadding ->
                        LoginActivity(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}