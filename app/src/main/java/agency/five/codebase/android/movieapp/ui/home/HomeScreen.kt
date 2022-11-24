package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeMapperImpl
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val popularMovieCategories = mutableListOf<MovieCategory>(
    MovieCategory.POPULAR_STREAMING,
    MovieCategory.POPULAR_ON_TV,
    MovieCategory.POPULAR_IN_THEATRES,
    MovieCategory.POPULAR_FOR_RENT
)

private val nowPlayingMovieCategories = mutableListOf<MovieCategory>(
    MovieCategory.NOW_PLAYING_MOVIES,
    MovieCategory.NOW_PLAYING_TV,
)

private val upcomingMovieCategories = mutableListOf<MovieCategory>(
    MovieCategory.UPCOMING_TODAY,
    MovieCategory.UPCOMING_THIS_WEEK,
)

private val homeMapper:HomeScreenMapper = HomeMapperImpl()

val popularCategoryViewState = homeMapper.toHomeMovieCategoryViewState(popularMovieCategories,MovieCategory.POPULAR_STREAMING,MoviesMock.getMoviesList())
val nowPlayingCategoryViewState = homeMapper.toHomeMovieCategoryViewState(nowPlayingMovieCategories,MovieCategory.NOW_PLAYING_MOVIES,MoviesMock.getMoviesList())
val upcomingCategoryViewState = homeMapper.toHomeMovieCategoryViewState(upcomingMovieCategories,MovieCategory.UPCOMING_TODAY,MoviesMock.getMoviesList())

@Composable
fun HomeScreen(
){
    Column(Modifier.padding(5.dp) .verticalScroll(rememberScrollState())) {
        CategoryView(categoryName = "What's Popular", categoryViewState = popularCategoryViewState)
        CategoryView(categoryName = "Now Playing", categoryViewState = nowPlayingCategoryViewState)
        CategoryView(categoryName = "Upcoming", categoryViewState = upcomingCategoryViewState)
    }

}

@Composable
fun CategoryView(
    modifier:Modifier = Modifier,
    categoryName:String,
    categoryViewState:HomeMovieCategoryViewState,
){
    val fontFamily = FontFamily(
        Font(R.font.titilliumweb_bold, FontWeight.Bold),
        Font(R.font.titilliumweb_light, FontWeight.Light)
    )
    
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = categoryName,
            fontFamily = fontFamily,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )

        LazyRow {
            items(
                items = categoryViewState.movieCategories,
                key = {category ->  category.itemId},
            ){ category ->
                
                MovieCategoryLabel(movieCategoryLabelUiState = category, onItemClick = {},modifier = Modifier.padding(8.dp))
            }
        }

        LazyRow {
            items(
                items = categoryViewState.movies,
                key = {movie ->  movie.id},
            ){ movie ->
               MovieCard(movieCardViewState = MovieCardViewState(movie.imageUrl!!,movie.isFavorite), onCardClick = { /*TODO*/ }, modifier = Modifier
                   .padding(5.dp)
                   .size(width = 140.dp, height = 200.dp), onLikeButtonClick = { })
            }

        }

    }
}
