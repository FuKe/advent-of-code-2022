import java.util.*

fun main() {
    val rawPuzzleInput: List<String> = readInput("Day05.txt")

    val instructions: List<Instruction> = parseInstructions(rawPuzzleInput.subList(10, rawPuzzleInput.size))
    val stacks: Map<Int, Stack<String>> = parseStacks(rawPuzzleInput.subList(0, 9))

    val resultPartOne: String = partOne(stacks, instructions)
    println("Result part one: $resultPartOne")

    val resultPartTwo: String = partTwo(stacks, instructions)
    println("Result part two: $resultPartTwo")
}

private fun partOne(stacks: Map<Int, Stack<String>>, instructions: List<Instruction>): String {
    val mutableStacks = createDeepCopy(stacks)

    instructions.forEach {
        for (i in 1..it.amountToMove) {
            mutableStacks[it.to]!! += mutableStacks[it.from]!!.pop()
        }
    }

    return mutableStacks.map { (_, stack) ->
        stack.peek()
    }.joinToString()
        .replace(", ", "")
}

private fun partTwo(stacks: Map<Int, Stack<String>>, instructions: List<Instruction>): String {
    val mutableStacks = createDeepCopy(stacks)

    instructions.forEach { instruction ->
        val cratesToMove = IntRange(1, instruction.amountToMove).map {
            mutableStacks[instruction.from]!!.pop()
        }.reversed()
        mutableStacks[instruction.to]!! += cratesToMove
    }

    return mutableStacks.map { (_, stack) ->
        stack.peek()
    }.joinToString()
        .replace(", ", "")
}

private fun parseStacks(cargoShip: List<String>): Map<Int, Stack<String>> {
    val stackNumbers: String = cargoShip[8]

    // Extract the stack numbers and map them to their string position index
    // (as that's also the index to find the crates for that particular stack)
    val indexByStackNumber: Map<Int, Int> =
        stackNumbers.mapIndexed { index, char ->
            if (char.isWhitespace()) {
                null
            } else {
                Pair(char.digitToInt(), index)
            }
        }.filterNotNull().toMap()

    val parsedStacks: MutableMap<Int, Stack<String>> = indexByStackNumber.map { (stackNumber, index) ->
        stackNumber to Stack<String>()
    }.toMap().toMutableMap()

    cargoShip.take(8).reversed().forEach {
        indexByStackNumber.forEach { (stackNumber, index) ->
            if (it.length > index) {
                val char = it[index]
                if (char.isLetter()) {
                    parsedStacks[stackNumber]!! += char.toString()
                }
            }
        }
    }

    return parsedStacks.toMap()
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

// Creates a >>deep copy<< of the given map, as ".toMutableMap()" only creates a shallow copy!
private fun createDeepCopy(from: Map<Int, Stack<String>>): MutableMap<Int, Stack<String>> =
    from.map { (stackNumber, stack) ->
        val stackCopy = Stack<String>()
        stackCopy.addAll(stack)
        stackNumber to stackCopy
    }.toMap().toMutableMap()

private class Instruction(
    val amountToMove: Int,
    val from: Int,
    val to: Int
)
