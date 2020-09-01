package repositories.scores

import com.joao.simsschool.R

private enum class Situations {
    SUCCESS,
    WARNING,
    ERROR
}

class ScoresCourseResponse(
    var av1: Int = 0,
    var av2: Int = 0,
    var name: String = "",
    var skips: Number = 0
) {
    fun initService(result: Map<String, Any?>) {
        av1 = (result["av1"] as? Number ?: 0).toInt()
        av2 = (result["av2"] as? Number ?: 0).toInt()
        name = (result["name"] as? String ?: "")
        skips = (result["skips"] as? Number ?: 0)
    }

    private fun getColors(situation: Situations): Pair<Int, Int> {
        return when(situation) {
            Situations.ERROR -> Pair(R.color.redSmooth, R.color.red)
            Situations.WARNING -> Pair(R.color.yellowSmooth, R.color.yellow)
            Situations.SUCCESS -> Pair(R.color.greenSmooth, R.color.green)
        }
    }

    private fun getSituation(): Situations {
        val situations = arrayListOf<Int>(
            skipsColor().first,
            averageScoreColors().first
        )

        for (situation in situations) {
            if(situation == R.color.redSmooth) {
                return Situations.ERROR
            }
            if(situation == R.color.yellowSmooth) {
                return Situations.WARNING
            }
        }

        return Situations.SUCCESS
    }

    fun averageScore(): String =  ((av1.toDouble() + av2.toDouble()) / 2.0).toString()

    fun skipsPercentage(): String = skips.toString().plus("%")

    fun averageScoreColors(): Pair<Int, Int> {
        val score = (av1.toDouble() + av2.toDouble()) / 2.0

        return when {
            score <= 4 -> {
                getColors(Situations.ERROR)
            }
            score <= 7 -> {
                getColors(Situations.WARNING)
            }
            else -> {
                getColors(Situations.SUCCESS)
            }
        }
    }

    fun skipsColor(): Pair<Int, Int> {
        val skips = skips.toInt()

        return when {
            skips <= 75 -> {
                getColors(Situations.ERROR)
            }
            skips <= 85 -> {
                getColors(Situations.WARNING)
            }
            else -> {
                getColors(Situations.SUCCESS)
            }
        }
    }

    fun av1Color(): Pair<Int, Int> {
        val av1 = av1.toInt()

        return when {
            av1 <= 4 -> {
                getColors(Situations.ERROR)
            }
            av1 <= 7 -> {
                getColors(Situations.WARNING)
            }
            else -> {
                getColors(Situations.SUCCESS)
            }
        }
    }

    fun av2Color(): Pair<Int, Int> {
        val av2 = av2.toInt()

        return when {
            av2 <= 4 -> {
                getColors(Situations.ERROR)
            }
            av2 <= 7 -> {
                getColors(Situations.WARNING)
            }
            else -> {
                getColors(Situations.SUCCESS)
            }
        }
    }

    fun getSituationText(): String {
        return when(getSituation()) {
            Situations.ERROR -> "Disapproved"
            Situations.WARNING -> "Recovery"
            Situations.SUCCESS -> "Approved"
        }
    }

    fun getTextSituationColor(): Int {
        return when(getSituation()) {
            Situations.ERROR -> R.color.red
            Situations.WARNING -> R.color.yellow
            Situations.SUCCESS ->  R.color.green
        }
    }
}
