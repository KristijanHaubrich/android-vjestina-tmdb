package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


data class MovieCardViewState(
    val imageUrl: String,
    val isFavorite: Boolean = false,
)


@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movieCardViewState: MovieCardViewState,
    onCardClick: () -> Unit,
    onLikeButtonClick: () -> Unit,
) {

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
    ) {
        Box(modifier = Modifier.fillMaxSize().clickable { onCardClick }, ) {

            val bannerPainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieCardViewState?.imageUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.ic_movie_placeholder)
                    .crossfade(1000)
                    .error(R.drawable.ic_movie_placeholder)
                    .build(),
                contentScale = ContentScale.Crop,
            )

            if (bannerPainter.state is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator()
            }

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = bannerPainter,
                contentDescription = "banner_picture",
            )

            FavoriteButton(isFavorite = movieCardViewState.isFavorite, onClick = { onLikeButtonClick })

            }


        }

    }


@Preview
@Composable
private fun MovieCardPreview() {
    val movieMock = MoviesMock.getMoviesList()[0]

    movieMock?.let { movie ->
        val movieState = MovieCardViewState(
            imageUrl = movie.imageUrl!!,
            isFavorite = movie.isFavorite,
        )

        MovieCard(movieCardViewState = movieState, onCardClick = {  }) {

        }
    }





}
