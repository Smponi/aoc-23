fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { it.getFirstAndLastDigitAsInt() }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { it.replaceNameOfDigitsWithNumber().getFirstAndLastDigitAsInt() }
    }

    val input = readInput("Day01")
    check(input.size==1000)

    part1(input).println()


    val part2Test = part2(readInput("Day01Test"))
    check(part2Test == 281)
    part2(input).println()
}
