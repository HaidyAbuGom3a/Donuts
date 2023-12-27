package org.haidy.support.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
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
                val viewModel: MainViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                if(state.isCompleted){
                    SupportNavGraph(navController = navController, state.destination)
                }
            }
        }
    }
}