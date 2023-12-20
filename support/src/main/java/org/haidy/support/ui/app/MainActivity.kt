package org.haidy.support.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.haidy.support.ui.navigation.SupportNavGraph
import org.haidy.support.ui.theme.SupportTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupportTheme {
                val navController = rememberNavController()
                SupportNavGraph(navController = navController, "Login")
            }
        }
    }
}