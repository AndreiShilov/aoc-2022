fun main() {
    fun getCalMap(input: List<String>): MutableMap<Int, Int> {
        val calMap = mutableMapOf<Int, Int>()
        var counter = 0
        var acc = 0

        input.forEach { it ->
            if (it.isBlank()) {
                calMap[counter] = acc
                counter++
                acc = 0
            } else {
                acc += it.toInt()
            }
        }

        if (acc != 0) calMap[counter] = acc

        return calMap
    }

    fun getCalMap2(input: List<String>): List<Int> {
        val list = mutableListOf<Int>()
        var acc = 0
        for (line in input) {
            if (line.isNotBlank()) {
                acc += line.toInt(); continue
            }
            list += acc
            acc = 0
        }

        if (acc != 0) list += acc

        return list
    }

    fun part1(input: List<String>): Int {
        return getCalMap2(input).max()
    }

    fun part2(input: List<String>): Int {
        return getCalMap2(input)
            .sortedDescending()
            .subList(0, 3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
