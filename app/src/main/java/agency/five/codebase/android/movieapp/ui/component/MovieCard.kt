package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
        onClick = onCardClick,
        ) {

        Box(modifier = Modifier.fillMaxSize()) {

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
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = if (isFavorite) R.drawable.ic_favorites_full else R.drawable.ic_favorites_empty),
        colorFilter = ColorFilter.tint(White),
        contentDescription = null,
        modifier = modifier
            .size(dimensionResource(id = R.dimen.favorite_button_size))
            .clip(CircleShape)
            .clickable { onClick() }
            .padding(MaterialTheme.spacing.small)
    )
}

@Preview
@Composable
private fun MovieCardPreview() {


}
