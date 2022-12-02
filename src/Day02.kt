fun main() {

    fun pair(inputLine: String): Pair<Char, Char> {
        val split = inputLine.split(" ")
        val opponent = split[0].toCharArray()[0]
        val we = split[1].toCharArray()[0]
        return Pair(opponent, we)
    }

    fun solve1(inputLine: String): Int {
        val (opponent, we) = pair(inputLine)

        val game = we.code - opponent.code
        println("Opponent - [${opponent}], we [${we}] game [$game]")
        val result = when (game) {
            22, 25 -> 0
            23 -> 3
            24, 21 -> 6
            else -> throw IllegalStateException("Noooooo")
        }

        return (we.code - 87) + result
    }

    fun solve2(inputLine: String): Int {
        val (opponent, we) = pair(inputLine)

        val result = when (we) {
            'X' -> 0 + ((opponent.code + 22) % 3)
            'Y' -> 3 + ((opponent.code + 23) % 3)
            'Z' -> 6 + ((opponent.code + 24) % 3)
            else -> throw IllegalStateException("Noooooo")
        }
        println("Opponent - [${opponent}], we [${result}]")

        return (we.code - 87) + result
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { solve1(it) }
    }


    fun part2(input: List<String>): Int {
        return input.sumOf { solve2(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
//    println(part2(input))
}
