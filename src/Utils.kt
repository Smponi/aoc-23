import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Utility function for day 1
 */
fun String.getFirstAndLastDigitAsInt(): Int {
    return this.filter { it.isDigit() }.getFirstAndLast().toInt()
}

fun String.getFirstAndLast() = "${first()}${last()}"

/**
 * Junk way to add the int representation of the word.
 * E.g. If the string contains "one" it will be replaced with "one1one"
 */
fun String.replaceNameOfDigitsWithNumber(): String {
    println("Before switching: $this")
    val result =
        this
            .replace("eight", "eight8eight")
            .replace("two", "two2two")
            .replace("one", "one1one")
            .replace("four", "four4four")
            .replace("five", "five5five")
            .replace("six", "six6six")
            .replace("seven", "seven7seven")
            .replace("nine", "nine9nine")
            .replace("three", "three3three")
    "After switching: $result".println()
    return result
}
