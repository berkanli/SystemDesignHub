package okhttp

fun main() {
    val baseUrl = "https://jsonplaceholder.typicode.com/posts"

    // Example: GET request
    val getResponse = OkHttpHelper.getRequest("$baseUrl/1")
    println("GET Response: $getResponse")

    // Example: POST request
    val postBody = """
        {
            "title": "foo",
            "body": "bar",
            "userId": 1
        }
    """.trimIndent()
    val postResponse = OkHttpHelper.postRequest(baseUrl, postBody, "application/json; charset=utf-8")
    println("POST Response: $postResponse")

    // Example: PUT request
    val putBody = """
        {
            "id": 1,
            "title": "updated title",
            "body": "updated body",
            "userId": 1
        }
    """.trimIndent()
    val putResponse = OkHttpHelper.putRequest("$baseUrl/1", putBody, "application/json; charset=utf-8")
    println("PUT Response: $putResponse")

    // Example: DELETE request
    val deleteResponse = OkHttpHelper.deleteRequest("$baseUrl/1")
    println("DELETE Response: $deleteResponse")
}