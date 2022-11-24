package agency.five.codebase.android.movieapp.ui.home.mapper

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelTextViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieCategoryViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieViewState

class HomeMapperImpl: HomeScreenMapper {
    override fun toHomeMovieCategoryViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory,
        movies: List<Movie>
    ): HomeMovieCategoryViewState {

        val homeMovieCategoryViewStates = mutableListOf<HomeMovieViewState>()

        movies.forEach{ movie ->
            val homeMovieCategoryViewState = HomeMovieViewState(
                id = movie.id,
                imageUrl = movie.imageUrl,
                isFavorite = movie.isFavorite
            )

            homeMovieCategoryViewStates.add(homeMovieCategoryViewState)
        }

        val movieCategoryLabelViewStates = mutableListOf<MovieCategoryLabelViewState>()

        movieCategories.forEach{ movieCategory ->
            val movieCategoryLabelViewState = MovieCategoryLabelViewState(
                itemId = movieCategory.ordinal,
                categoryText = MovieCategoryLabelTextViewState.TextRes(
                    when(movieCategory){
                        MovieCategory.NOW_PLAYING_MOVIES -> R.string.movies
                        MovieCategory.NOW_PLAYING_TV -> R.string.TV
                        MovieCategory.POPULAR_FOR_RENT -> R.string.for_rent
                        MovieCategory.POPULAR_IN_THEATRES -> R.string.in_theatres
                        MovieCategory.POPULAR_ON_TV -> R.string.on_tv
                        MovieCategory.POPULAR_STREAMING -> R.string.streaming
                        MovieCategory.UPCOMING_TODAY -> R.string.today
                        MovieCategory.UPCOMING_THIS_WEEK -> R.string.this_week

                    }

                ),
                isSelected = if(selectedMovieCategory.name == movieCategory.name) true else false
            )

            movieCategoryLabelViewStates.add(movieCategoryLabelViewState)
        }

        return HomeMovieCategoryViewState(
            movies = homeMovieCategoryViewStates,
            movieCategories = movieCategoryLabelViewStates
        )

    }

}
