package basics

/*
    Here are the main characteristics of the with function:
        Context object is available as this.

        It returns the result of a lambda.

        It isn't an extension function.

    What do we mean when we say that with isn't an extension function? It means that the context object is passed
    as an argument — it is enclosed in parentheses. However, inside the lambda our object is available as a receiver (this).

    with is used in two cases:

    First, when we want to do something with the context object and don't want to receive a result.
    Remember — with returns the result of a lambda, but according to Kotlin code conventions, we use this scope function
    when we don't need a certain result. Truly, "What Happens in with, stays in with".
 */

fun main() {
    val musicians = mutableListOf("Thom York", "Jonny Greenwood", "Colin Greenwood")
    with(musicians) {
        println("'with' is called with the argument $this")
        println("List contains $size elements")
    } // We print the needed data and don't try to get a certain result

    val firstAndLast = with(musicians) {
        "First list element - ${first()}," +
                " last list element - ${last()}"
    }
    println(firstAndLast) // We create a new variable firstAndLast and pass the result of calculations inside the function body to it. After that we print this variable.
}