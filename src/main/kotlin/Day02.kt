const val DRAW_SCORE = 3
const val WIN_SCORE = 6

val testInput = listOf("A Y", "B X", "C Z")

fun main() {
    val testPuzzleInput = convertPuzzleInput(testInput)
    val puzzleInput = convertPuzzleInput(readInput("Day02.txt"))

    val testPartOne = partOne(testPuzzleInput)
    check(testPartOne == 15)

    val resultPartOne = partOne(puzzleInput)
    println("Result part one: $resultPartOne")

    val testPartTwo = partTwo(testPuzzleInput)
    check(testPartTwo == 12)

    val resultPartTwo = partTwo(puzzleInput)
    println("Result part two: $resultPartTwo")
}

fun partOne(puzzleInput: List<Pair<String, String>>): Int =
    puzzleInput.sumOf {
        val myHand = RockPaperScissors.valueOf(it.second)
        when {
            myHand.equals == it.first -> DRAW_SCORE + myHand.score
            myHand.defeats == it.first -> WIN_SCORE + myHand.score
            else -> myHand.score
        }
    }

fun partTwo(puzzleInput: List<Pair<String, String>>): Int {
    val resultCodeMap = mapOf("X" to ResultCode.LOSS, "Y" to ResultCode.DRAW, "Z" to ResultCode.WIN)
    return puzzleInput.sumOf {
        val resultCode = resultCodeMap[it.second]!!
        when (resultCode) {
            ResultCode.WIN -> WIN_SCORE + RockPaperScissors.values().single { rpc -> rpc.defeats == it.first }.score
            ResultCode.DRAW -> DRAW_SCORE + RockPaperScissors.values().single { rpc -> rpc.equals == it.first }.score
            ResultCode.LOSS -> RockPaperScissors.values().single { rpc -> rpc.equals != it.first && rpc.defeats != it.first }.score
        }
    }
}

fun convertPuzzleInput(rawPuzzleInput: List<String>): List<Pair<String, String>> =
    rawPuzzleInput.mapNotNull {
        if (it.isEmpty()) {
            null
        } else {
            val split: List<String> = it.split(" ")
            Pair(split[0], split[1])
        }
    }

enum class RockPaperScissors(val equals: String, val score: Int, val defeats: String) {
    X("A", 1, "C"),
    Y("B", 2, "A"),
    Z("C", 3, "B")

    // A/X = Rock, B/Y = Paper, C/Z = Scissors
}

enum class ResultCode {
    WIN, LOSS, DRAW
}
