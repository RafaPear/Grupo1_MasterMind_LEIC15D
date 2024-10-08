import kotlin.random.Random

// Variable/constant set up
const val MAX_TRIES = 10 // in 5..20
const val SIZE_POSITIONS = 4 // in 2..6
const val SIZE_COLORS = 6 // in 2..10 and >= SIZE_POSITIONS
const val FIRST_COLOR = 'A' // ‘A’ or ‘a’ or ‘0’
val COLORS = FIRST_COLOR ..< FIRST_COLOR+SIZE_COLORS

fun main() {
    val secret: String = generateSecret()
    println(secret)
    checkRepeated(generateSecret())
    //println("Find the code in $MAX_TRIES attempts.")
    //println("$SIZE_POSITIONS positions and $SIZE_COLORS colors $COLORS")
    //for (numTries in 1..MAX_TRIES) {
    //    val guess = readGuess(numTries)
    //    if (guess == secret) {
    //        println("Congratulations!\nYou got it right on your ${numTries}th try.")
    //        return
    //    }
    //    val corrects = getCorrects(guess, secret)
    //    val swapped = getSwapped(guess, secret)
    //    printTry(numTries, guess, corrects, swapped)
    //}
    //println("You missed $MAX_TRIES attempts.")
}

//Generates a random sequence of 4 chars
fun generateSecret(): String {
    var returnStr = ""
    for (i in 0..SIZE_POSITIONS-1) {
        returnStr += COLORS.random()
    }
    return returnStr
}

fun checkRepeated(str: String): Boolean {
    for (i in 0..SIZE_POSITIONS-1) {
        for (j in i..SIZE_POSITIONS-1) {
            if (j != i){
                println("i: " + i)
                println("j: " + j)
            //if (str[i] != str[i+j])
            }
        }
    }
    return true
}

//TODO: Function readGuess()


//TODO: Function getCorrects()

//TODO: Function getSwapped()

//TODO: Function printTry()
