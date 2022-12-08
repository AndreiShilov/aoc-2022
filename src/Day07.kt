import java.util.Stack

fun main() {


    fun getSizesMap(input: List<String>): Map<String, Long> {
        val mutableMapOf = mutableMapOf<String, Long>()

        val folders = Stack<String>()

        for (line in input) {

            if (line.startsWith("$ cd") && !line.startsWith("$ cd ..")) {
                val dirName = line.substring(5)
                val dir = folders.elements().asSequence().plus(dirName).joinToString("_")
                folders.push(dir)
                continue
            }

            if (line.startsWith("$ ls") || line.startsWith("dir")) {
                continue
            }

            if (line.startsWith("$ cd ..")) {
                folders.pop()
                continue
            }

            val toLong = line.split(" ")[0].toLong()

            folders.elements().toList().forEach {
                val orPut = mutableMapOf.getOrPut(it) { 0 }
                mutableMapOf[it] = orPut + toLong
            }
        }

        return mutableMapOf
    }

    fun part1(input: List<String>): Long {
        return getSizesMap(input).filter { it.value < 100000 }.map { it.value }.sum()
    }

    fun part2(input: List<String>): Long {
        val sizesMap = getSizesMap(input)

        val root = sizesMap.getOrDefault("/", 0)

        val available = 70000000 - root
        println("Available $available")
        val needToFreeUp = 30000000 - available
        println("Need to free up $needToFreeUp")

        return sizesMap.filter { it.value > needToFreeUp }.map { it.value }.min()
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day07_test")) == 95437L)
    println(part2(readInput("Day07_test")))
    check(part2(readInput("Day07_test")) == 24933642L)



    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
