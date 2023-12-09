fun main() {
    /**
     * Get the maximum value for each color. Return them multiplied
     */
    fun calculatePart2(inputMap: Map<String, MutableList<Pair<Int, Int>>>): Int {
        val blue = inputMap.getOrDefault("blue", emptyList()).maxOf { it.second }
        val green = inputMap.getOrDefault("green", emptyList()).maxOf { it.second }
        val red = inputMap.getOrDefault("red", emptyList()).maxOf { it.second }
        return blue * red * green
    }

    fun isPossible(
        inputMap: Map<String, MutableList<Pair<Int, Int>>>,
        reds: Int,
        blues: Int,
        greens: Int,
        gameId: Int
    ): Int {
        inputMap.getOrDefault("blue", emptyList()).find { it.second > blues }?.let { return 0 }
        inputMap.getOrDefault("green", emptyList()).find { it.second > greens }?.let { return 0 }
        inputMap.getOrDefault("red", emptyList()).find { it.second > reds }?.let { return 0 }
        return gameId
    }

    fun String.parseDay2(): Pair<Int, Map<String, MutableList<Pair<Int, Int>>>> {
        val parts = this.split(":")
        val gameId = parts[0].trim().replace("Game ", "").toInt()

        val colorGroups = parts[1].split(";").map { it.trim() }

        val colorMap = mutableMapOf<String, MutableList<Pair<Int, Int>>>()

        var idCounter = 1

        for (colorGroup in colorGroups) {
            val colorParts = colorGroup.split(",").map { it.trim() }

            for (colorPart in colorParts) {
                val colorCount = colorPart.split(" ")
                val color = colorCount[1].trim()
                val count = colorCount[0].toInt()

                colorMap.computeIfAbsent(color) { mutableListOf() }
                    .add(Pair(idCounter, count))

            }

            idCounter++
        }

        return Pair(gameId, colorMap)
    }

    fun part1(input: List<String>): Int {
        val red = 12
        val blue = 14
        val green = 13
        return input.sumOf {
            val temp = it.parseDay2()
            isPossible(temp.second, red, blue, green, temp.first)
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            calculatePart2(it.parseDay2().second)
        }
    }

    val testInput = readInput("Day02Test")
    require(part1(testInput) == 8)
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()


}