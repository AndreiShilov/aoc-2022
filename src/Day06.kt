import java.io.File
import java.util.Stack

fun main() {

    fun solve(input: String, msgSize: Int): Int {
        var marker = 0
        val windowed = input.toCharArray().toList().asSequence().windowed(msgSize)
        for (window in windowed) {
            marker++
            if (window.toSet().size == msgSize) break
        }
        return marker + msgSize - 1
    }

    fun part1(input: String): Int {
        return solve(input, 4)
    }

    fun part2(input: String): Int {
        return solve(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day06_test")[1]) == 5)
    check(part1(readInput("Day06_test")[2]) == 6)
    check(part1(readInput("Day06_test")[3]) == 10)
    check(part1(readInput("Day06_test")[4]) == 11)
    check(part2(readInput("Day06_test")[0]) == 19)
    check(part2(readInput("Day06_test")[1]) == 23)
    check(part2(readInput("Day06_test")[2]) == 23)
    check(part2(readInput("Day06_test")[3]) == 29)
    check(part2(readInput("Day06_test")[4]) == 26)
//    check(part2(name = "Day05_test") == "MCD")


    val input = readInput("Day06")
    println(part1(input[0]))
    println(part2(input[0]))
}
