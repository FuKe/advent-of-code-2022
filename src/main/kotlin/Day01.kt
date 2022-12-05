fun main() {
    val puzzleInput: List<String> = readInput("Day01.txt")
    partOne(puzzleInput)
    partTwo(puzzleInput)
}

private fun partOne(puzzleInput: List<String>) {
    var highestSum = 0
    var currentSum = 0

    for (line in puzzleInput) {
        if (line.isNotEmpty()) {
            currentSum += line.toInt()
        } else if (line.isEmpty() && currentSum > highestSum) {
            highestSum = currentSum
            currentSum = 0
        } else if (line.isEmpty() && currentSum < highestSum) {
            currentSum = 0
        }
    }

    println("Part1 answer: $highestSum")
}

private fun partTwo(puzzleInput: List<String>) {
    val sums = mutableListOf<Int>()

    var currentSum = 0
    for (line in puzzleInput) {
        if (line.isNotEmpty()) {
            currentSum += line.toInt()
        } else {
            sums += currentSum
            currentSum = 0
        }
    }

    val result = sums.sortedDescending()
        .subList(0, 3)
        .sum()

    println("Part2 answer: $result")
}
