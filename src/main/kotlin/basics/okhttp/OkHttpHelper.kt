package okhttp

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

object OkHttpHelper {

    private val client = OkHttpClient()

    private val defaultHeaders = mapOf(
        "User-Agent" to "OkHttp Example"
    )

    fun getRequest(url: String, headers: Map<String, String> = defaultHeaders): String? {
        return executeRequest(url, headers) { builder -> builder.get() }
    }

    fun postRequest(
        url: String,
        body: String,
        contentType: String = "text/plain; charset=utf-8",
        headers: Map<String, String> = defaultHeaders
    ): String? {
        return executeRequestWithBody(url, body, contentType, headers) { builder, requestBody ->
            builder.post(requestBody)
        }
    }

    fun putRequest(
        url: String,
        body: String,
        contentType: String = "text/plain; charset=utf-8",
        headers: Map<String, String> = defaultHeaders
    ): String? {
        return executeRequestWithBody(url, body, contentType, headers) { builder, requestBody ->
            builder.put(requestBody)
        }
    }

    fun deleteRequest(
        url: String,
        body: String? = null,
        contentType: String = "text/plain; charset=utf-8",
        headers: Map<String, String> = defaultHeaders
    ): String? {
        return if (body == null) {
            executeRequest(url, headers) { builder -> builder.delete() }
        } else {
            executeRequestWithBody(url, body, contentType, headers) { builder, requestBody ->
                builder.delete(requestBody)
            }
        }
    }

    // Generic executor for requests without body
    private fun executeRequest(
        url: String,
        headers: Map<String, String>,
        applyMethod: (Request.Builder) -> Request.Builder
    ): String? {
        val requestBuilder = Request.Builder().url(url)
        headers.forEach { (key, value) -> requestBuilder.addHeader(key, value) }
        val request = applyMethod(requestBuilder).build()

        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    println("Request failed: ${response.message}")
                    null
                } else {
                    response.body?.string()
                }
            }
        } catch (e: IOException) {
            println("Request error: ${e.message}")
            null
        }
    }

    // Generic executor for requests with body
    private fun executeRequestWithBody(
        url: String,
        body: String,
        contentType: String,
        headers: Map<String, String>,
        applyMethod: (Request.Builder, RequestBody) -> Request.Builder
    ): String? {
        val mediaType = contentType.toMediaType()
        val requestBody = body.toRequestBody(mediaType)

        val requestBuilder = Request.Builder().url(url)
        headers.forEach { (key, value) -> requestBuilder.addHeader(key, value) }
        val request = applyMethod(requestBuilder, requestBody).build()

        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    println("Request failed: ${response.message}")
                    null
                } else {
                    response.body?.string()
                }
            }
        } catch (e: IOException) {
            println("Request error: ${e.message}")
            null
        }
    }
}


