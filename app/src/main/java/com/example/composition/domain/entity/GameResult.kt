package com.example.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class GameResult(
    val winner: Boolean,
    val countOfRightAnswer: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
) : Parcelable {
    val scorePercent: Int
        get() = ((countOfRightAnswer / countOfQuestions.toDouble()) * 100).toInt()
}