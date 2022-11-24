package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun FavoritesButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(if(isFavorite) R.drawable.ic_favorites_full else R.drawable.ic_favorites_empty)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_movie_placeholder),
        contentDescription = "faovritesIcon",
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.favorite_button_size))
            .padding(dimensionResource(id = R.dimen.favorite_button_padding)),
        contentScale = ContentScale.Crop,
    )

}

@Preview(showBackground = true)
@Composable
private fun FavoriteButtonPreview() {
    var isFavorite = true
    FavoritesButton(
        isFavorite = isFavorite,
        onClick = { isFavorite = !isFavorite }
    )
}
