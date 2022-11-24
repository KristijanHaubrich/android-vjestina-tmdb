package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter


data class ActorCardViewState(
    val imageUrl: String,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    modifier: Modifier = Modifier,
    actorCardViewState: ActorCardViewState,
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        ) {

        val fontFamily = FontFamily(
            Font(R.font.titilliumweb_bold, FontWeight.Bold),
            Font(R.font.titilliumweb_light, FontWeight.Light)
        )

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


                val bannerPainter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(actorCardViewState.imageUrl)
                        .crossfade(true)
                        .placeholder(R.drawable.ic_actor_image_placeholder)
                        .crossfade(1000)
                        .error(R.drawable.ic_actor_image_placeholder)
                        .build(),
                    contentScale = ContentScale.Crop,
                    
                )

                if (bannerPainter.state is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator()
                }

                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = bannerPainter,
                    contentDescription = "banner_picture",
                )


            Text(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.movieDetailsOverviewTitlePadding)),
                text = actorCardViewState.name,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 15.sp,
            )

            Text(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.movieDetailsOverviewTitlePadding)),
                text = actorCardViewState.character,
                fontFamily = fontFamily,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                fontSize = 15.sp,
            )

        }
    }
}

@Preview
@Composable
private fun ActorCardPreview() {
    val mockActorCardViewState = MoviesMock.getActor().let { actor ->
        ActorCardViewState(
            imageUrl = actor.imageUrl.orEmpty(),
            name = actor.name,
            character = actor.character,
        )
    }

    ActorCard(
        actorCardViewState = mockActorCardViewState,
        modifier = Modifier
            .padding(20.dp)
            .size(
                120.dp,
                180.dp,
            ),
    )
}
