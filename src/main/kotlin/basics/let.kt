package basics

/*
    Here are the main features of the let function:

        Context object is available as it.

        It returns the result of a lambda.

    `let` is used in two general cases:

    First, when we want to do something with the safety call operator ? and non-null objects — yes, let allows us
    to do that. Let's see: in the code below, we try to perform some operations with a nullable string (String?).
    If we use the standard method, the compiler throws an error. To avoid that, we can check if str is null or non-null
    when let is called. And do remember that let returns the result of a lambda, which is it.length in our case
    — the last lambda expression (the last line in the lambda's body).
 */


fun main() {
    val str: String? = "Jonny Greenwood"
//processNonNullString(str)       // compilation error: str can be null

    val length = str?.let {
        println("let() is called on $it")
        it.length
    }

    println(length)


    val musicians = listOf("Thom York", "Jonny Greenwood", "Colin Greenwood")
    val modifiedFirstItem = musicians.first().let { firstItem ->
        println("The first item of the list is '$firstItem'")
        if (firstItem.length >= 5) firstItem else "!" + firstItem + "!"
    }.uppercase()
    println("First item after modifications: '$modifiedFirstItem'")
}