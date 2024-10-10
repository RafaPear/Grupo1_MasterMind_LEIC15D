//master BRANC

// Variable/constant set up
const val MAX_TRIES = 10 // in 5..20
const val SIZE_POSITIONS = 4 // in 2..6
const val SIZE_COLORS = 6 // in 2..10 and >= SIZE_POSITIONS
const val FIRST_COLOR = 'A' // ‘A’ or ‘a’ or ‘0’
val COLORS = FIRST_COLOR ..< FIRST_COLOR+SIZE_COLORS

fun main() {
    val secret: String = generateSecret()
    println(generateSecret())
    println(generateSecret())
    println(generateSecret())
    println(generateSecret())
    println(generateSecret())
    println("Find the code in $MAX_TRIES attempts.")
    println("$SIZE_POSITIONS positions and $SIZE_COLORS colors $COLORS")
    for (numTries in 1..MAX_TRIES) {
        val guess = readGuess(numTries)
        if (guess == secret) {
            println("Congratulations!\nYou got it right on your ${numTries}th try.")
            return
        }
        val corrects = getCorrects(guess, secret)
        val swapped = getSwapped(guess, secret)
        printTry(numTries, guess, corrects, swapped)
    }
    println("You missed $MAX_TRIES attempts.")
}

// Generates a random a String of 4 different Chars
fun generateSecret(): String {
    var returnStr = ""

    while (true) {
        for (i in 0..<SIZE_POSITIONS)
            returnStr += COLORS.random()

        if (checkRepeated(returnStr))
            returnStr = ""
        else break
    }
    return returnStr
}

// Checks if a String has repeated Chars.
fun checkRepeated(str: String): Boolean {
    for (i in 0..<SIZE_POSITIONS)
        for (j in 0..<SIZE_POSITIONS)
            if (i != j)
                if (str[j] == str[i])
                    return true
    return false
}

// Prompts the user to input their guess
// for the current attempt number and
// returns the guess as a string.
// The function must only allow an input
// length equal to 4 Chars and with the
// valid Chars
fun readGuess(tries: Int): String {
    while (true){
        var matchesFound = 0
        when (tries) {
            1 -> print("1st attempt: ")
            2 -> print("2nd attempt: ")
            3 -> print("3rd attempt: ")
            else -> print("${tries}th attempt: ")
        }
        val readValue: String = readln()

        for (i in 0..<SIZE_POSITIONS) {
            if (readValue.length != SIZE_POSITIONS) break
            for (j in 0..<SIZE_COLORS)
                if (readValue[i] == FIRST_COLOR + j) matchesFound++
        }

        if (readValue.length != SIZE_POSITIONS || matchesFound != SIZE_POSITIONS)
            println("Invalid attempt!")

        else if (matchesFound == SIZE_POSITIONS) return readValue
    }
}

// Checks how many guessed Chars are in the
// same position as the answer.
fun getCorrects(guess: String, answer: String): Int{
    var tempReturn = 0

    for (i in 0..<SIZE_POSITIONS)
        if (guess[i] == answer[i])
            tempReturn++
    return tempReturn
}

// Checks how many guessed Chars are in
// the answer but on the wrong place.
fun getSwapped(guess: String, answer: String): Int {
    var tempReturn = 0

    for (i in 0..<SIZE_POSITIONS)
        for (j in 0..<SIZE_POSITIONS)
            if (i != j)
                if (guess[i] == answer[j])
                    tempReturn++
    return tempReturn
}

// Prints the results in a nice and readable way
// shown as (example): "1st: ADFC -> 1C + 3T".
// The syntax is presented as:
// (numTries): (guess) -> (corrects) + (swapped)
fun printTry(numTries: Int, guess: String, corrects: Int, swapped: Int){
    var strOut = ""
    when(numTries) {
        1 -> strOut += "1st: "
        2 -> strOut += "2nd: "
        3 -> strOut += "3rd: "
        else -> strOut += "${numTries}th: "
    }
    strOut += "$guess -> ${corrects}C + ${swapped}T"
    println(strOut)
}
