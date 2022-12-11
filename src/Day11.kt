fun main() {

    fun part1(input: List<String>): Long {

        val monkeys = input.chunked(7).map { it.toMonkey() }.toList()

        val lcm = monkeys.map { it.divider() }.reduce { acc, value ->
            acc * value
        }

        println(lcm)

        repeat(10000) {
            monkeys.forEach {
                it.round(monkeys, lcm)
            }
        }


        monkeys.forEachIndexed { i, value ->
            println("Monkey # $i have times ${value.timesInspected}")
        }

        val take = monkeys.map { it.timesInspected }.sortedDescending().take(2)
        return take[0] * take[1]
    }

    fun part2(input: List<String>) {

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")

    println(part1(testInput))
//    check(part1(testInput) == 10605L)
    check(part1(testInput) == 2713310158)


    val input = readInput("Day11")
    println(part1(input))
//    part2(input)
}

private fun List<String>.toMonkey(): Monkey {
    val monkeyIndex = this[0].filter { it.isDigit() }[0].digitToInt().toLong() // probably we do not need it
    val initialInspectionList =
        this[1]
            .split("Starting items: ")[1].split(",")
            .map { it.trim() }
            .map { it.toLong() }
            .toMutableList()
    val (sign, value) = this[2].replace("  Operation: new = old ", "").split(" ")
    val divider = this[3].replace("  Test: divisible by ", "").toInt()
    val tI = this[4].replace("    If true: throw to monkey ", "").toInt()
    val fI = this[5].replace("    If false: throw to monkey ", "").toInt()

    return Monkey(
        inspectionQueue = initialInspectionList,
        operation = getOperationFunc(sign, value),
        decider = getDeciderFun(tI, fI),
        divider
    )
}

private fun getDeciderFun(trueIndex: Int, falseIndex: Int): (Boolean) -> Int {
    return fun(result: Boolean): Int {
        if (result) return trueIndex
        return falseIndex
    }
}

private fun getOperationFunc(sign: String, value: String): (Long) -> Long {

    when (sign) {
        "+" -> return fun(old: Long): Long {
            if (value == "old") return old + old
            return old + value.toInt()
        }

        "*" -> return fun(old: Long): Long {
            if (value == "old") return old * old
            return old * value.toInt()
        }
    }
    throw NotImplementedError("Boom")
}

class Monkey(
    private var inspectionQueue: MutableList<Long> = mutableListOf(),
    private val operation: (Long) -> Long,
    private val decider: (Boolean) -> Int,
    private val divider: Int,
) {
    var timesInspected = 0L

    fun round(monkeys: List<Monkey>, lcm: Int = 3) {
        timesInspected += inspectionQueue.size

        inspectionQueue.forEach {
            val newWorryLvl = operation(it) % lcm

//            println(newWorryLvl)
            monkeys[decider(newWorryLvl % divider == 0L)].addItem(newWorryLvl)
        }

        inspectionQueue = mutableListOf()
    }

    fun timesInspected() = timesInspected

    fun divider() = divider

    fun addItem(item: Long) {
        inspectionQueue.add(item)
    }
}
