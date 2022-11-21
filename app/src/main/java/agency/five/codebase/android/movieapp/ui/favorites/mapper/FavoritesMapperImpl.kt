package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImpl: FavoritesMapper {
    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState {
        val movies_states = mutableListOf<FavoritesMovieViewState>()

        favoriteMovies.forEach { movie ->
            val movie_state = FavoritesMovieViewState(
                id = movie.id,
                isFavorite = movie.isFavorite,
                imageUrl = movie.imageUrl
            )

            movies_states.add(movie_state)
        }

        return FavoritesViewState(movies_states)
    }

}
