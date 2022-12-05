private val testInput = listOf(
    "vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg",
    "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw"
)

private const val LOWERCASE_ASCII_CODE_DIFF = 96
private const val UPPERCASE_ASCII_CODE_DIFF = 64

fun main() {
    val puzzleInput: List<String> = readInput("Day03.txt")

    val testPartOne = partOne(testInput)
    check(testPartOne == 157)

    val resultPartOne = partOne(puzzleInput)
    println("Result part one: $resultPartOne")

    val testPartTwo = partTwo(testInput)
    check(testPartTwo == 70)

    val resultPartTwo = partTwo(puzzleInput)
    println("Result part two: $resultPartTwo")
}

private fun partOne(puzzleInput: List<String>): Int {
    return puzzleInput.sumOf {
        val left = it.substring(0, it.length / 2)
        val right = it.substring(it.length / 2)

        val inBoth: Char = left.filter { char -> char in right }[0]
        calculatePriority(inBoth)
    }
}

private fun partTwo(puzzleInput: List<String>): Int {
    val currentGroup = mutableListOf<String>()
    var sumPriorities = 0
    puzzleInput.forEachIndexed { index, s ->
        currentGroup += s

        if ((index + 1) % 3 == 0) {
            val first = currentGroup[0]
            val second = currentGroup[1]
            val third = currentGroup[2]

            val inAll: Char = first.filter { char -> char in second && char in third }[0]
            sumPriorities += calculatePriority(inAll)

            currentGroup.clear()
        }
    }

    return sumPriorities
}

private fun calculatePriority(c: Char): Int {
    val lowercaseUppercaseDiff = 26
    return if (c.isUpperCase()) {
        c.code - UPPERCASE_ASCII_CODE_DIFF + lowercaseUppercaseDiff
    } else {
        c.code - LOWERCASE_ASCII_CODE_DIFF
    }
}
