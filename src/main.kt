// Variable/constant set up
const val MAX_TRIES = 10 // in 5..20
const val SIZE_POSITIONS = 4 // in 2..6
const val SIZE_COLORS = 6 // in 2..10 and >= SIZE_POSITIONS
const val FIRST_COLOR = 'A' // ‘A’ or ‘a’ or ‘0’
val COLORS = FIRST_COLOR ..< FIRST_COLOR+SIZE_COLORS

fun main() {
    val secret: String = generateSecret()
    println("Find the code in $MAX_TRIES attempts.")
    println("$SIZE_POSITIONS positions and $SIZE_COLORS colors $COLORS")
    for (numTries in 1..MAX_TRIES) {
        val guess = readGuess(numTries)
        if (guess == secret) {
            println("Congratulations!\nYou got it right on your ${numTries}th try.")
            return
        }
        println(getCorrects(guess, secret))
//        val corrects = getCorrects(guess, secret)
//        val swapped = getSwapped(guess, secret)
//        printTry(numTries, guess, corrects, swapped)
    }
    println("You missed $MAX_TRIES attempts.")
}

// Generates a random a String of 4 diferent Chars
fun generateSecret(): String {
    var returnStr = ""
    var tempB = true
    while (tempB) {
        for (i in 0..SIZE_POSITIONS - 1)
            returnStr += COLORS.random()

        if (checkRepeated(returnStr))
            returnStr = ""
        else tempB = false
    }
    return returnStr
}

// Checks if a String has repeated Chars.
fun checkRepeated(str: String): Boolean {
    var isRepeated = false
    for (i in 0..SIZE_POSITIONS-1)
        for (j in 0..SIZE_POSITIONS-1)
            if (i != j)
                if (str[j] == str[i])
                    isRepeated = true

    return isRepeated
}

// Prompts the user to input  their guess
// for the current attempt number and
// returns the guess as a string. The attempt
// input (numTries) is probably just for cosmetic printing
// purposes. The function must only allow an input equal to 4 Chars

fun readGuess(tries: Int): String {

    var readValue: String = ""
    while (true) {
        var matchesFound: Int = 0

        when (tries) {
            1 -> print("1st attempt: ")
            2 -> print("2nd attempt: ")
            3 -> print("3rd attempt: ")
            else -> print("${tries}th attempt: ")
        }
        var readValue: String = readln()

        for (i in 0..SIZE_POSITIONS - 1)
            for (j in 0..SIZE_COLORS - 1)
                if (readValue[i] == FIRST_COLOR+j) matchesFound++
        if (readValue.length != 4 || matchesFound != SIZE_POSITIONS) {
            println("Invalid attempt!")
            matchesFound = 0
        }
        else if (matchesFound == SIZE_POSITIONS) return readValue
    }
}

/*
        var cont = 0
        for (i in readValue){
            cont ++
            if (i in COLORS && readValue.length == 4){
                if (cont == 4){
                    matchesFound++
                    return readValue
                }
            } else {
                println("Invalid attempt!")
                break
            }
        }
*/



// Checks how many guessed Chars are in the
// same position as the answer.

fun getCorrects(guess: String, answer: String): Int{
    var tempReturn: Int = 0
    for (i in 0..SIZE_POSITIONS-1)
        if (guess[i] == answer[i])
            tempReturn++

    return tempReturn
}


// TODO: Function getSwapped()
// Checks how many guessed Chars are in the
// the answer but on the wrong place.


// TODO: Function printTry()
// Prints
