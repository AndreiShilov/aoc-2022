fun main() {


    fun intersectSum(intersect: Set<Char>) = intersect
        .map { charInIntersect: Char -> charInIntersect.code }
        .map { code ->
            println("Code ${code}")
            val i = when (code) {
                in 97..122 -> code - 96
                in 65..90 -> code - 64 + 26
                else -> throw IllegalStateException("Boom")
            }
            println(i)
            i
        }.sum()

    fun part1(input: List<String>): Int {
        return input.map {
            val length = it.length
            val split = length / 2
            val firstBag = it.substring(0, split).toCharArray()
            val secondBag = it.substring(split).toCharArray()

            println("First [${String(firstBag)}] Second [${String(secondBag)}]")

            val intersect = firstBag.intersect(secondBag.asIterable().toSet())

            println("Intersect [$intersect]")

            intersectSum(intersect)
        }.sum()
    }


    fun part2(input: List<String>): Int {
        return input.chunked(3).map {
            val first = it[0].toCharArray()
            val second = it[1].toCharArray()
            val third = it[2].toCharArray()

            first.intersect(second.toSet()).intersect(third.toSet())

            val intersect = first.intersect(second.toSet()).intersect(third.toSet())

            println("Intersect [$intersect]")

            intersectSum(intersect)
        }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
