package basics.generic_object_extension

data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)