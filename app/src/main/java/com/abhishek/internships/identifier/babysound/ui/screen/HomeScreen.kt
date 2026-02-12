package com.abhishek.internships.identifier.babysound.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhishek.internships.identifier.babysound.ui.permission.RequestAudioPermission
import com.abhishek.internships.identifier.babysound.viewmodel.BabySoundViewModel

@Composable
fun HomeScreen(vm: BabySoundViewModel) {

    var hasPermission by remember { mutableStateOf(false) }

    if (!hasPermission) {
        RequestAudioPermission {
            hasPermission = true
        }
        return
    }

    val result by vm.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = vm::analyze) {
            Text("Record Baby Sound")
        }

        Spacer(Modifier.height(24.dp))

        result?.let {
            Text(it.meaning, style = MaterialTheme.typography.headlineSmall)
            Text("Confidence: ${(it.confidence * 100).toInt()}%")
        }
    }
}
