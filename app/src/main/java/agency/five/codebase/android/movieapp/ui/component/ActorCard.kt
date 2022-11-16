package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
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
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.Dp


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
        elevation = 5.dp,
        ) {

        val fontFamily = FontFamily(
            Font(R.font.titilliumweb_bold, FontWeight.Bold),
            Font(R.font.titilliumweb_light, FontWeight.Light)
        )

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(actorCardViewState?.imageUrl)
                .crossfade(true)
                .placeholder(R.drawable.ic_actor_image_placeholder)
                .crossfade(1000)
                .error(R.drawable.ic_actor_image_placeholder)
                .build(),
            contentScale = ContentScale.Crop,

            )

        if (painter.state is AsyncImagePainter.State.Loading){
            CircularProgressIndicator()
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(R.dimen.heightActorImage.dp)
                    .padding(R.dimen.paddingActorImage.dp)
                ,
                painter = painter,
                contentDescription = "actor_picture",
            )

            Text(
                modifier = Modifier.padding(R.dimen.actorCardTextNamePadding.dp),
                text = actorCardViewState.name,
                textAlign = TextAlign.Left,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(start = R.dimen.actorCardTextCharacterPaddingStart.dp, top = R.dimen.actorCardTextCharacterPaddingTop.dp, bottom = R.dimen.actorCardTextCharacterPaddingBottom.dp),
                text = actorCardViewState.character,
                textAlign = TextAlign.Left,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Light
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
