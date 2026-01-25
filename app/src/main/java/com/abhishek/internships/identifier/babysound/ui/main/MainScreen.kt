package com.abhishek.internships.identifier.babysound.ui.main


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abhishek.internships.identifier.babysound.data.BabySoundRepository
import com.abhishek.internships.identifier.babysound.ui.theme.BabyBlue
import com.abhishek.internships.identifier.babysound.ui.theme.MintGreen
import com.abhishek.internships.identifier.babysound.ui.theme.SoftPink
import com.abhishek.internships.identifier.babysound.ui.theme.WarmYellow

@Composable
fun MainScreen() {
    val sounds = BabySoundRepository.getSounds()
    val colors = listOf(BabyBlue, SoftPink, MintGreen, WarmYellow)

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ){
        item {
            Header()
        }
        itemsIndexed(sounds) { index, sound ->
            SoundCard(
                sound = sound,
                bgColor = colors[index % colors.size]
            )
        }
    }
}

