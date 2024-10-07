package com.plataformas.lab7.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    title: String = "App Title",
    showBackButton: Boolean = false
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (showBackButton) {
                run {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null)
                    }
                }
            } else {
                null
            }
        }
    )
}
