package agency.five.codebase.android.movieapp.ui.favorites

data class FavoritesMovieViewState (
    val id: Int,
    val isFavorite: Boolean,
    val imageUrl: String?,
)

data class FavoritesViewState(
    val movies_states: List<FavoritesMovieViewState>,
)

