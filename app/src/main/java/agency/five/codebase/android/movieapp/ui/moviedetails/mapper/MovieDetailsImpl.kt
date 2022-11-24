package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsImpl: movieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        val movieDetailsViewState = MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl!!,
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crew = movieDetails.crew,
            cast = movieDetails.cast
        )
        return movieDetailsViewState
    }
}


