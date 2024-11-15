package system_design.movie_ticket_booking.services

import system_design.movie_ticket_booking.model.Movie

class MovieService {
    private val movies = mutableMapOf<String, Movie>()

    fun addMovie(title: String, genre: String, duration: Int): Movie {
        val movieID = generateMovieID()
        val movie = Movie(movieID, title, genre, duration)
        movies[movieID] = movie
        return movie
    }

    fun getMovie(movieID: String): Movie? {
        return movies[movieID]
    }

    fun listMovies(): List<Movie> {
        return movies.values.toList()
    }

    private fun generateMovieID(): String{
        return "movie_${System.currentTimeMillis()}"
    }

}