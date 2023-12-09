fun main() {
    fun part1(input: List<String>): Int {
        val parser = EngineSchematicParser(input)
        return parser.sumPartNumbers()
    }

    val input = readInput("Day03")
    part1(input).println()
}
class EngineSchematicParser(private val schematic: List<String>) {
    // Check up,down,left,right,diagonal
    private val deltaX = listOf(-1, -1, -1, 0, 0, 1, 1, 1)
    private val deltaY = listOf(-1, 0, 1, -1, 1, -1, 0, 1)

    fun sumPartNumbers(): Int {
        var sum = 0
        for (row in schematic.indices) {
            var col = 0
            while (col < schematic[row].length) {
                if (schematic[row][col].isDigit()) {
                    val number = extractNumber(row, col)
                    // Check each digit of the number for adjacency to a symbol
                    for (digitCol in col until col + number.toString().length) {
                        if (isAdjacentToSymbol(row, digitCol)) {
                            sum += number
                            break
                        }
                    }
                    col += number.toString().length
                } else {
                    col++
                }
            }
        }
        return sum
    }

    private fun isAdjacentToSymbol(row: Int, col: Int): Boolean {
        for (k in deltaX.indices) {
            val newRow = row + deltaX[k]
            val newCol = col + deltaY[k]
            if (newRow in schematic.indices && newCol in schematic[newRow].indices) {
                val charAtNewPosition = schematic[newRow][newCol]
                if (!charAtNewPosition.isDigit() && charAtNewPosition != '.') {
                    return true
                }
            }
        }
        return false
    }

    private fun extractNumber(row: Int, col: Int): Int {
        var numberStr = ""
        var currentCol = col
        while (currentCol < schematic[row].length && schematic[row][currentCol].isDigit()) {
            numberStr += schematic[row][currentCol]
            currentCol++
        }
        return numberStr.toInt()
    }
}
