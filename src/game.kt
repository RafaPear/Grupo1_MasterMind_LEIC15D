// Trabalho 1 de PG-LEIC15D Ano Letivo 24/25
// Professor: Nuno Leite
// Grupo I: Francisco Mendes, Gustavo Costa e Rafael Pereira

// Generates a random a String of 4 different Chars
fun generateSecret(): String {
    while (true) {
        var returnStr = ""
        for (i in 0..<SIZE_POSITIONS)
            returnStr += COLORS.random()
        if (!checkRepeated(returnStr))
            return returnStr
    }
}

// Checks if a given String has repeated Chars.
fun checkRepeated(str: String): Boolean {
    for (i in 0..<SIZE_POSITIONS)
        for (j in 0..<SIZE_POSITIONS)
            if (i != j)
                if (str[j] == str[i])
                    return true
    return false
}

// Prompts the user to input their guess
// for the current attempt number and checks
// if the guess is a valid answer.
// Returns the guess as a String.
fun readGuess(tries: Int): String {
    while (true){
        when (tries) {
            1 -> print("1st attempt: ")
            2 -> print("2nd attempt: ")
            3 -> print("3rd attempt: ")
            else -> print("${tries}th attempt: ")
        }
        val readValue: String = readln()
        if (readCheck(readValue))
            println("Invalid attempt!")
        else return readValue
    }
}

// Checks if a String Argument (guess: String)
// matches all the requirements to be a
// valid input Guess. Returns true
// if the argument passes the check,
// false if it does not.
fun readCheck(guess: String): Boolean {
    var matchesFound = 0
    for (i in 0..<SIZE_POSITIONS) {
        if (guess.length != SIZE_POSITIONS) break
        for (j in 0..<SIZE_COLORS)
            if (guess[i] == FIRST_COLOR + j)
                matchesFound++
    }
    return (guess.length != SIZE_POSITIONS || matchesFound != SIZE_POSITIONS)
}

// Checks how many guessed Chars are in the
// same position as the answer.
fun getCorrects(guess: String, answer: String): Int{
    var corrects = 0
    for (i in 0..<SIZE_POSITIONS)
        if (guess[i] == answer[i])
            corrects++
    return corrects
}

// Checks how many guessed Chars are in
// the answer but on the wrong place.
fun getSwapped(guess: String, answer: String): Int {
    var swapped = 0
    for (i in 0..<SIZE_POSITIONS)
        for (j in 0..<SIZE_POSITIONS)
            if (i != j)
                if (guess[i] == answer[j])
                    swapped++
    return swapped
}

// Prints the results in a nice and readable way
// shown as, for example: "1st: ADFC -> 1C + 3T".
// The syntax is presented as:
// (numTries): (guess) -> (corrects) + (swapped)
fun printTry(numTries: Int, guess: String, corrects: Int, swapped: Int){
    var strOut = when(numTries) {
        1 -> "1st: "
        2 -> "2nd: "
        3 -> "3rd: "
        else -> "${numTries}th: "
    }
    strOut += "$guess -> ${corrects}C + ${swapped}T"
    println(strOut)
}