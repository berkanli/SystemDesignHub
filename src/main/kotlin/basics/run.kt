package basics

/*
    Let's look at the characteristics of the run function:

        Context object is available as this.

        It returns the result of a lambda.

    run is like with, but it is an extension function. Thus, run does the same thing as with but is invoked like let.

    When can we use run? Mostly in two cases:

    First, when we want to initialize a new object and pass the result of a lambda to it. It is important
    — our new object is independent and valuable, unlike in the case of the with function. For instance,
    in the code below, we create a new object result, pass a new value to the service element port, and pass
    to result the result of the query() function with the prepareRequest() function concatenated with a string
    as a parameter. Note! The value of service.port is changed.
 */



fun main() {
    condition1()
    println("-------------------")
    condition2()
}

class MultiportService(var url: String, var port: Int) {
    fun prepareRequest(): String = "Default request"
    fun query(request: String): String = "Result for query '$request'"
}

fun condition1() {
    val service = MultiportService("https://example.kotlinlang.org", 80)

    val result = service.run {
        port = 8080
        query(prepareRequest() + " to port $port")
    }
    println(result)
}

/*
    Second, when we want to use a function without an extension and execute a block of several operators.
    In that case, we don't use a context object and just organize some piece of code related to the variable hexNumberRegex.
 */

fun condition2() {
    val hexNumberRegex = run {
        val digits = "0-9"
        val hexDigits = "A-Fa-f"
        val sign = "+-"

        Regex("[$sign]?[$digits$hexDigits]+")
    }

    for (match in hexNumberRegex.findAll("+1234 -FFFF not-a-number")) {
        println(match.value)
    }
}