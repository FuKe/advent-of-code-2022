private const val testInput = "bvwbjplbgvbhsrlpgdmjqwftvncz"

fun main() {
    val puzzleInput: String = readInput("Day06.txt")[0]

    val testPartOne = detectMarker(testInput, 4)
    check(testPartOne == 5)

    val resultPartOne = detectMarker(puzzleInput, 4)
    println("Result part one: $resultPartOne")

    val resultPartTwo = detectMarker(puzzleInput, 14)
    println("Result part two: $resultPartTwo")
}

fun detectMarker(puzzleInput: String, distinctStringLength: Int): Int {
    puzzleInput.forEachIndexed { index, _ ->
        val sub = puzzleInput.subSequence(index, index + distinctStringLength)

        // Filter out duplicates by converting subsequence to a set
        if (sub.toSet().size == distinctStringLength) {
            return index + distinctStringLength
        }
    }

    return -1
}
