package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


data class MovieCardViewState(
    val imageUrl:String,
    val isFavorite: Boolean = false,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState
){

    Card(
        modifier = Modifier
            .width(110.dp)
            .height(200.dp)
            .padding(8.dp)
            .clickable { },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,

        ) {

        Box(modifier = Modifier.fillMaxSize()){

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

            if (bannerPainter.state is AsyncImagePainter.State.Loading){
                CircularProgressIndicator()
            }

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = bannerPainter,
                contentDescription = "banner_picture",
            )

            if (movieCardViewState.isFavorite){
                val favoritesPainter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.ic_favorites_full)
                        .build()
                )

                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(25.dp)
                        .height(25.dp),
                    painter = favoritesPainter,
                    contentDescription = "favorites_ic",
                )
            }
            else{
                val favoritesPainter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.ic_favorites_empty)
                        .crossfade(true)
                        .crossfade(1000)
                        .build()
                )

                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(25.dp)
                        .height(25.dp),
                    painter = favoritesPainter,
                    contentDescription = "favorites_ic",
                )
            }


        }

    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    val movie1 = MovieCardViewState("https://i.pinimg.com/736x/8e/ff/8f/8eff8f87c3c3f4ee0086f73f86943c21.jpg")
    val movie2 = MovieCardViewState("https://i.pinimg.com/736x/8e/ff/8f/8eff8f87c3c3f4ee0086f73f86943c21.jpg",true)
    MovieCard(movieCardViewState = movie1)
    MovieCard(movieCardViewState = movie2)
}