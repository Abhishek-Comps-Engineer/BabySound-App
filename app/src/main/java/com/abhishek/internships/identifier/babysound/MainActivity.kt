package com.abhishek.internships.identifier.babysound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.abhishek.internships.identifier.babysound.ui.screen.HomeScreen
import com.abhishek.internships.identifier.babysound.viewmodel.BabySoundViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: BabySoundViewModel = hiltViewModel()
            HomeScreen(vm)
        }
    }
}
