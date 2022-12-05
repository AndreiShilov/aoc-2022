import java.io.File
import java.util.Stack

fun main() {

    data class Move(
        val amount: Int,
        val from: Int,
        val to: Int,
    )

    fun readInput(name: String) = File("src", "$name.txt").readText().split("\n\n")

    fun getMoves(moves: String) = moves
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.split(" ") }
        .map { moveArray -> Move(moveArray[1].toInt(), moveArray[3].toInt(), moveArray[5].toInt()) }
        .toList()

    fun setUpInitialCrates(crates: String): List<Stack<Char>> {
        val cratesLines = crates.split("\n").reversed()

        val stacksNumbersArray = cratesLines.first().split(Regex(" \\D "))

        val cratesStacks = List(stacksNumbersArray.size) { Stack<Char>() }
        cratesLines.subList(1, cratesLines.size).forEach { it ->
            it.toCharArray()
                .toList()
                .chunked(4)
                .forEachIndexed { index, it ->
                    val char = it[1]
                    if (char != ' ') cratesStacks[index].push(char)
                }
        }
        return cratesStacks
    }


    fun solve(name: String, action: Function2<Move, List<Stack<Char>>, Unit>): String {
        val readInput = readInput(name)
        val crates = readInput[0]
        val moves = readInput[1]

        println(crates)
        println(moves)

        val cratesStacks = setUpInitialCrates(crates)

        println(cratesStacks)

        val movesList = getMoves(moves)

        movesList.forEach { move -> action(move, cratesStacks) }

        println(cratesStacks)

        return cratesStacks
            .filter { it.isNotEmpty() }
            .map { it.peek() }
            .joinToString("")
    }

    fun part1(name: String): String {
        return solve(name) { move, cratesStacks ->
            for (time in 1..move.amount) {
                cratesStacks[move.to - 1].push(cratesStacks[move.from - 1].pop())
            }
        }
    }

    fun part2(name: String): String {
        return solve(name) { move, cratesStacks ->
            val tmpList = mutableListOf<Char>()
            for (time in 1..move.amount) {
                tmpList.add(cratesStacks[move.from - 1].pop())

            }
            tmpList.reversed().forEach { cratesStacks[move.to - 1].push(it) }
        }
    }

    // test if implementation meets criteria from the description, like:
    check(part1(name = "Day05_test") == "CMZ")
    check(part2(name = "Day05_test") == "MCD")


    val input = readInput("Day05")
    println(part1(name = "Day05"))
    println(part2(name = "Day05"))
}
