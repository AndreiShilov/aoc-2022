import java.util.Stack

fun main() {

    fun part1(input: List<String>): Int {
        val matrix = input.map { row -> row.toCharArray().toList().map { it.digitToInt() } }

        var counter = 0

        for (i in 1..input.size - 2) {
            for (j in 1..input.size - 2) {
                val value = matrix[i][j]

                //left
                var visible = true
                for (k in j - 1 downTo 0) {
                    if (matrix[i][k] >= value) {
                        visible = false
                        break
                    }
                }

                if (visible) {
                    counter++;
                    continue
                }

                //right
                visible = true
                for (k in j + 1 until input.size) {
                    if (matrix[i][k] >= value) {
                        visible = false
                        break
                    }
                }

                if (visible) {
                    counter++;
                    continue
                }

                //top
                visible = true
                for (k in i - 1 downTo 0) {
                    if (matrix[k][j] >= value) {
                        visible = false
                        break
                    }
                }

                if (visible) {
                    counter++;
                    continue
                }

                //bottom
                visible = true
                for (k in i + 1 until input.size) {
                    if (matrix[k][j] >= value) {
                        visible = false
                        break
                    }
                }

                if (visible) {
                    counter++
                }

            }
        }


        return (input.size * 4 - 4) + counter
    }

    fun calculateLeft(
        j: Int,
        matrix: List<List<Int>>,
        i: Int,
        value: Int
    ): Int {
        var counter = 0
        for (k in j - 1 downTo 0) {
            if (matrix[i][k] >= value) {
                return ++counter
            }
            counter++
        }
        return counter
    }

    fun calculateRight(
        j: Int,
        matrix: List<List<Int>>,
        i: Int,
        value: Int
    ): Int {
        var counter = 0
        for (k in j + 1 until matrix.size) {
            if (matrix[i][k] >= value) {
                return ++counter
            }
            counter++

        }
        return counter
    }

    fun calculateTop(
        i: Int,
        matrix: List<List<Int>>,
        j: Int,
        value: Int
    ): Int {
        var counter = 0
        for (k in i - 1 downTo 0) {
            if (matrix[k][j] >= value) {
                return ++counter
            }
            counter++
        }
        return counter
    }

    fun calculateBottom(
        i: Int,
        matrix: List<List<Int>>,
        j: Int,
        value: Int
    ): Int {
        var counter = 0
        for (k in i + 1 until matrix.size) {
            if (matrix[k][j] >= value) {
                return ++counter
            }
            counter++
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        val matrix = input.map { row -> row.toCharArray().toList().map { it.digitToInt() } }

        val scoresSet = mutableSetOf<Int>()

        for (i in input.indices) {
            for (j in input.indices) {
                val value = matrix[i][j]

                val left = calculateLeft(j, matrix, i, value)
                val right = calculateRight(j, matrix, i, value)
                val top = calculateTop(i, matrix, j, value)
                val bottom = calculateBottom(i, matrix, j, value)

                scoresSet.add(left * right * top * bottom)
            }
        }


        return scoresSet.max()
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day08_test")) == 21)
    check(part2(readInput("Day08_test")) == 8)


    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
