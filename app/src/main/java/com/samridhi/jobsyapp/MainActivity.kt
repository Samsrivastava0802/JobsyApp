package com.samridhi.jobsyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.samridhi.jobsyapp.navigation.AppNavGraph
import com.samridhi.jobsyapp.navigation.AppNavigationActions
import com.samridhi.jobsyapp.ui.theme.JobsyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobsyAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    JobsyApp(
                        onNavigationEnd = {
                            finish()
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun JobsyApp(
    onNavigationEnd: () -> Unit
) {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController()
    val navActions = remember(navController) {
        AppNavigationActions(navController, onNavigationEnd)
    }

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        modifier = Modifier.fillMaxSize(),
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        AppNavGraph(
            navController = navController,
            navActions = navActions
        )
    }
}