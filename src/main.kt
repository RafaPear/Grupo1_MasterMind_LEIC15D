// Alternate BRANCH

// Trabalho 1 de PG-LEIC15D Ano Letivo 24/25
// Professor: Nuno Leite
// Grupo I: Francisco Mendes, Gustavo Costa e Rafael Pereira

// variable/constant set up
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
        val corrects = getCorrects(guess, secret)
        val swapped = getSwapped(guess, secret)
        printTry(numTries, guess, corrects, swapped)
    }
    println("You missed $MAX_TRIES attempts.")
}

// generates a random a String of 4 different Chars
fun generateSecret(): String {
    var returnStr = ""
    while (returnStr.length < SIZE_POSITIONS) {
        val randomChar = COLORS.random()
        if (randomChar !in returnStr) {
            returnStr += randomChar
        }
    }
    return returnStr
}

// prompts the user to input their guess
// for the current attempt number and
// returns the guess as a string. the attempt
// input (numTries) is probably just for cosmetic printing
// purposes. the function must only allow an input equal to 4 Chars
fun readGuess(tries: Int): String {
    while (true){
        when (tries) {
            1 -> print("1st attempt: ")
            2 -> print("2nd attempt: ")
            3 -> print("3rd attempt: ")
            else -> print("${tries}th attempt: ")
        }

        val readValue: String = toUpper(readln())

        if (!readCheck(readValue)) {
            println("Invalid attempt!")
        } else {
            return readValue
        }
    }
}

// Checks if a String Argument (guess: String)
// matches all the requirements to be a
// valid input Guess. Returns true
// if the argument passes the check,
// false if it does not.
fun readCheck(guess: String): Boolean {
    if (guess.length != SIZE_POSITIONS)
        return false

    for (eachChar in guess){
        if (eachChar !in COLORS)
            return false
    }
    return true
}

// if the user input any small letter in their code
// the program converts it to a respective capital letter
// to present a code with 4 capital letters (eg: input - abCD -> output - ABCD)
fun toUpper(strInput: String): String {
    var returnString = ""

    for (char in strInput){
        if (char in 'a'..'z') {
            returnString += char - 32
        } else if (char in 'A'..'Z'){
            returnString += char
        }
    }
    return returnString
}

// checks how many guessed Chars are in the
// same position as the answer.
fun getCorrects(guess: String, answer: String): Int{
    var tempReturn = 0
    for (i in 0..<SIZE_POSITIONS)
        if (guess[i] == answer[i])
            tempReturn++
    return tempReturn
}

// checks how many guessed Chars are in
// the answer but on the wrong place.
fun getSwapped(guess: String, answer: String): Int {
    var tempReturn = 0
    for (i in 0..<SIZE_POSITIONS)
        if (guess[i] in answer && guess[i] != answer[i])
            tempReturn++
    return tempReturn
}

// prints the results in a nice and readable way
// shown as (example): "1st: ADFC -> 1C + 3T".
// the syntax is presented as:
// (numTries): (guess) -> (corrects) + (swapped)
fun printTry(numTries: Int, guess: String, corrects: Int, swapped: Int){
    var strOut: String = when(numTries) {
        1 -> "1st: "
        2 -> "2nd: "
        3 -> "3rd: "
        else -> "${numTries}th: "
    }

    strOut += "$guess -> ${corrects}C + ${swapped}T"
    println(strOut)
}