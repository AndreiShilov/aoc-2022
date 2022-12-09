import java.util.Stack

fun main() {

    fun moveHead(head: Pair<Int, Int>, direction: String) =
        when (direction) {
            "R" -> Pair(head.first, head.second + 1)
            "L" -> Pair(head.first, head.second - 1)
            "U" -> Pair(head.first + 1, head.second)
            "D" -> Pair(head.first - 1, head.second)
            else -> throw NotImplementedError("Boom")
        }


    fun moveTail(head: Pair<Int, Int>, tail: Pair<Int, Int>): Pair<Int, Int> {

        if (head == tail) return tail // same spot

        val i = head.first - tail.first //row
        val j = head.second - tail.second //column

        if (head.first == tail.first) {
            if (j > 1) return Pair(tail.first, tail.second + 1)
            if (j < -1) return Pair(tail.first, tail.second - 1)
        }

        if (head.second == tail.second) {
            if (i > 1) return Pair(tail.first + 1, tail.second)
            if (i < -1) return Pair(tail.first - 1, tail.second)
        }

        if (i > 1 && j == 1) return Pair(tail.first + 1, tail.second + 1)
        if (i < -1 && j == 1) return Pair(tail.first - 1, tail.second + 1)

        if (i == 1 && j > 1) return Pair(tail.first + 1, tail.second + 1)
        if (i == 1 && j < -1) return Pair(tail.first + 1, tail.second - 1)

        if (i > 1 && j == -1) return Pair(tail.first + 1, tail.second - 1)
        if (i < -1 && j == -1) return Pair(tail.first - 1, tail.second - 1)

        if (i == -1 && j > 1) return Pair(tail.first - 1, tail.second + 1)
        if (i == -1 && j < -1) return Pair(tail.first - 1, tail.second - 1)

        if (i > 1 && j > 1) return Pair(tail.first + 1, tail.second + 1)
        if (i < -1 && j < -1) return Pair(tail.first - 1, tail.second - 1)

        if (i > 1 && j < -1) return Pair(tail.first + 1, tail.second - 1)
        if (i < -1 && j > 1) return Pair(tail.first - 1, tail.second + 1)

        return tail
    }

    fun part1(input: List<String>): Int {

        val tailUniquePositions = mutableSetOf<Pair<Int, Int>>()

        var head = Pair(0, 0)
        var tail = Pair(0, 0)

        tailUniquePositions.add(tail)
        for (line in input) {
            val (direction, distance) = line.split(" ")
            val dInt = distance.toInt()

            repeat(dInt) {
                head = moveHead(head, direction)
                tail = moveTail(head, tail)
                tailUniquePositions.add(tail)
            }
        }

        println(tailUniquePositions.size)
        return tailUniquePositions.size
    }


    fun part2(input: List<String>): Int {
        val tailUniquePositions = mutableSetOf<Pair<Int, Int>>()

        val rope = List(10) { Pair(0, 0) }.toMutableList()

        for (line in input) {
            val (direction, distance) = line.split(" ")
            val dInt = distance.toInt()

            println("Times $dInt")
            repeat(dInt) {
                rope[0] = moveHead(rope[0], direction)
                for (i in 0 until rope.size - 1) {

                    val tail = rope[i + 1]
                    rope[i + 1] = moveTail(rope[i], tail)
                }
                tailUniquePositions.add(rope.last())
            }
            println(rope)

        }

        return tailUniquePositions.size
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day09_test")) == 13)
    check(part2(readInput("Day09_test")) == 1)
    check(part2(readInput("Day09_test_extended")) == 36)


    val input = readInput("Day09")
    println("Part 1 = ${part1(input)}")
    println("Part 2 = ${part2(input)}")
}


