package com.team.cosmo_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.team.cosmo_compose.ui.AppNavHost
import com.team.cosmo_compose.ui.theme.COSMOcomposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            COSMOcomposeTheme {
                Scaffold(
                    containerColor = colorResource(id = R.color.white),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}
