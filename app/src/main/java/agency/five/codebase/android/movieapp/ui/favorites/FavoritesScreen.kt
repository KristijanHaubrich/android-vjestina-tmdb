package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.FavoritesButton
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()
val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesScreen(
    screen_state:FavoritesViewState = favoritesViewState,
    modifier: Modifier = Modifier,
){

    val fontFamily = FontFamily(
        Font(R.font.titilliumweb_bold, FontWeight.Bold),
        Font(R.font.titilliumweb_light, FontWeight.Light)
    )

    Column(
        modifier = modifier,
    ){
        Text(text = stringResource(id = R.string.favoritesScreenTitle),
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(dimensionResource(id = R.dimen.favoritesScreenTitlePadding)),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.favoritesScreenGridPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.favoritesScreenSpaceBetweenCards)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.favoritesScreenSpaceBetweenCards))
        ) {
            items(
                items = screen_state.movies_states,
                key = { movie_state ->
                    movie_state.id
                }
            ) { movie_state ->

                movie_state?.let{
                    val movieCardViewState = MovieCardViewState(imageUrl = it.imageUrl!!, isFavorite = it.isFavorite)

                    MovieCard(
                        movieCardViewState = movieCardViewState,
                        onCardClick = {  },
                        onLikeButtonClick = { }
                    )
                }

            }
        }




    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesScreen() {
    FavoritesScreen()
}


