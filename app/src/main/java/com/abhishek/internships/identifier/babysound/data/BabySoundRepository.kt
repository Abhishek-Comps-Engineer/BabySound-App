package com.abhishek.internships.identifier.babysound.data

import com.abhishek.internships.identifier.babysound.model.BabySound


object BabySoundRepository {

    fun getSounds(): List<BabySound> = listOf(
        BabySound(
            sound = "Neh",
            meaning = "Hungry",
            description = "Baby is hungry. Tongue presses against the roof of the mouth."
        ),
        BabySound(
            sound = "Owh",
            meaning = "Sleepy",
            description = "Yawning reflex indicates tiredness."
        ),
        BabySound(
            sound = "Heh",
            meaning = "Discomfort",
            description = "Baby may need diaper change or comfort."
        ),
        BabySound(
            sound = "Eairh",
            meaning = "Gas",
            description = "Air trapped in the stomach."
        ),
        BabySound(
            sound = "Eh",
            meaning = "Burp",
            description = "Baby needs to burp."
        )
    )
}
