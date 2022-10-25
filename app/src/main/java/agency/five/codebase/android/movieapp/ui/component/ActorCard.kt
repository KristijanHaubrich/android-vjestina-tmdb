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


data class ActorCardViewState(
    val imageUrl: String,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
) {
    Card(
        modifier = Modifier.width(110.dp).padding(8.dp),
        shape = RoundedCornerShape(15.dp),
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

        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                ,
                painter = painter,
                contentDescription = "actor_picture",
            )

            Text(
                modifier = Modifier.padding(5.dp),
                text = actorCardViewState.name,
                textAlign = TextAlign.Left,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(top = 5.dp, start = 5.dp, bottom = 10.dp),
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
    val actor1 = ActorCardViewState("https://media1.popsugar-assets.com/files/thumbor/msjEO8oq7PWud7pFv_Cm6GR6E-U/0x1:2771x2772/fit-in/500x500/filters:format_auto-!!-:strip_icc-!!-/2020/03/30/878/n/1922398/eb11f12e5e825104ca01c1.02079643_/i/Robert-Downey-Jr.jpg","Robert Downey JR.", "Tony Stark/IronMan")
    ActorCard(actorCardViewState = actor1)
}