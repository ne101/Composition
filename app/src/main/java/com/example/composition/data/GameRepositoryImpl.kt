package com.example.composition.data

import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question
import com.example.composition.domain.repository.GameRepository
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val option = HashSet<Int>()
        val rightAnswerSum = sum - visibleNumber
        option.add(rightAnswerSum)
        val from = max(rightAnswerSum - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswerSum + countOfOptions)
        while (option.size < countOfOptions) {
            option.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, option.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when(level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    50,
                    8
                )
            }
            Level.EASY -> {
                GameSettings(
                    50,
                10,
                    70,
                    60
                )
            }
            Level.MEDIUM -> {
                GameSettings(
                    200,
                    30,
                    80,
                    60
                )
            }
            Level.HARD -> {
                GameSettings(
                    1000,
                    50,
                    90,
                    60
                )
            }
        }
    }
}