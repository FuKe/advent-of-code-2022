private val testInput: List<String> = listOf(
    "2-4,6-8", "2-3,4-5", "5-7,7-9", "2-8,3-7", "6-6,4-6", "2-6,4-8"
)

fun main() {
    val puzzleInput: List<Pair<IntRange, IntRange>> = convertPuzzleInput(readInput("Day04.txt"))

    val testPartOne = partOne(convertPuzzleInput(testInput))
    check(testPartOne == 2)

    val resultPartOne = partOne(puzzleInput)
    println("Result part one: $resultPartOne")

    val resultPartTwo = partTwo(puzzleInput)
    println("Result part two: $resultPartTwo")
}

private fun convertPuzzleInput(puzzleInput: List<String>): List<Pair<IntRange, IntRange>> =
    puzzleInput.map {
        val split = it.split(",")

        val leftSectionSplit = split[0].split("-").map { str -> str.toInt() }
        val leftSection = IntRange(leftSectionSplit[0], leftSectionSplit[1])

        val rightSectionSplit = split[1].split("-").map { str -> str.toInt() }
        val rightSection = IntRange(rightSectionSplit[0], rightSectionSplit[1])

        Pair(leftSection, rightSection)
    }

private fun partOne(puzzleInput: List<Pair<IntRange, IntRange>>): Int =
    puzzleInput.count { (left, right) ->
        val intersectCount = left.intersect(right).count()
        intersectCount == left.count() || intersectCount == right.count()
    }

private fun partTwo(puzzleInput: List<Pair<IntRange, IntRange>>): Int =
    puzzleInput.count { (left, right) ->
        left.intersect(right).isNotEmpty()
    }
