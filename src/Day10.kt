import kotlin.math.abs

fun getCommandValue(command: String) = command.split(" ").last().toInt()
fun main() {

    fun part1(input: List<String>): Int {
        val computer = Computer()

        input.forEach { computer.executeCommand(it) }

        return computer.result()
    }

    fun part2(input: List<String>) {
        val computer = Computer()

        input.forEach { computer.executeCommand(it) }

        computer.printCrt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")

    check(part1(testInput) == 13140)

    val input = readInput("Day10")
    println(part1(input))
    part2(input)
}


class Computer {

    private val meaningfulCycles = List(6) { it -> it * 40 + 20 }.toSet()

    private var stack = 1
    private var cycles = 1

    private var results = mutableListOf<Int>()
    val crt = Array(6) { Array(40) { '.' } }

    fun executeCommand(command: String) {
        increase()
        if (command != "noop") {
            increase()
            stack += getCommandValue(command)
        }

    }

    fun result() = results.sum()

    fun printCrt() {
        crt.forEach { println(it.joinToString("")) }
    }

    private fun increase() {
        val col = (cycles - 1) % 40
        val row = (cycles - 1) / 40
        if (abs(stack - col) <= 1) crt[row][col] = '#'
        if (meaningfulCycles.contains(cycles)) results.add(cycles * stack)
        cycles++
    }
}

