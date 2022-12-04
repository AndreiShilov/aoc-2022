fun main() {

    fun checkSubRange(range1: Pair<Int, Int>, range2: Pair<Int, Int>): Boolean {
        val (firstStarts, firstEnd) = range1
        val (secondStarts, secondEnd) = range2

        if (firstStarts <= secondStarts && firstEnd >= secondEnd) return true
        if (secondStarts <= firstStarts && secondEnd >= firstEnd) return true

        return false;
    }

    fun checkIntersection(range1: Pair<Int, Int>, range2: Pair<Int, Int>): Boolean {
        val (firstStarts, firstEnd) = range1
        val (secondStarts, secondEnd) = range2

        for (i in firstStarts..firstEnd) {
            if (i in secondStarts..secondEnd) return true
        }

        return false
    }

    fun prepareInput(input: List<String>) = input
        .map { pair -> pair.split(',') }
        .map { pairs ->
            pairs.map { range -> range.split('-') }
                .map { rangeList -> Pair(rangeList[0].toInt(), rangeList[1].toInt()) }
        }

    fun part1(input: List<String>): Int {
        return prepareInput(input).count { checkSubRange(it[0], it[1]) }
    }


    fun part2(input: List<String>): Int {
        return prepareInput(input).count{checkIntersection(it[0], it[1])}
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val part1 = part1(testInput)
    println(part1)
    check(part1 == 2)
    val part2 = part2(testInput)
    println(part2)
    check(part2 == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
