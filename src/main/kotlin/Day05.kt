import java.util.*

fun main() {
    val rawPuzzleInput: List<String> = readInput("Day05.txt")

    val instructions: List<Instruction> = parseInstructions(rawPuzzleInput.subList(10, rawPuzzleInput.size))
    val stacks = parseStacks(rawPuzzleInput.subList(0, 9))
}

private fun parseStacks(rawStacks: List<String>): Map<Int, Stack<String>> {
    TODO()
}

private fun parseInstructions(rawInstructions: List<String>): List<Instruction> =
    rawInstructions.map {
        val moveTo = it.last().digitToInt()
        val moveFrom = it.substring(it.length - 6).first().digitToInt()
        val amountToMove = it.substring(it.length - 14)
            .trim()
            .split(" ")
            .first()
            .toInt()

        Instruction(amountToMove, moveFrom, moveTo)
    }
private class Instruction(
    val amountToMove: Int,
    val from: Int,
    val to: Int
)
