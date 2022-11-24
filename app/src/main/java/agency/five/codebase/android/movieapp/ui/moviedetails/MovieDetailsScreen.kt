package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsImpl
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.movieDetailsMapper
import agency.five.codebase.android.movieapp.ui.theme.spacing
import android.graphics.Paint.Align
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest


val NUMBER_OF_CREW_TO_DISPLAY = 6

private val movieDetailsMapper: movieDetailsMapper = MovieDetailsImpl()
val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsScreen(
    screen_state:MovieDetailsViewState = movieDetailsViewState,
    onLikeButtonClick:() -> Unit = {},
){
    val fontFamily = FontFamily(
        Font(R.font.titilliumweb_bold, FontWeight.Bold),
        Font(R.font.titilliumweb_light, FontWeight.Light)
    )

    Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()

            ){
                Banner(screen_state,fontFamily)
                Overview(screen_state,fontFamily)
                Crew(screen_state.crew)
                Cast(screen_state.cast, Modifier.padding(15.dp))
            }



        }

@Composable
fun Banner(
    screen_state:MovieDetailsViewState = movieDetailsViewState,
    fontFamily: FontFamily,
){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.movieDetailsBannerHeight))
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(screen_state?.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_movie_placeholder),
            contentDescription = "banner",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.movieDetailsBannerHeight)*0.4f)
        ) {
            Row{

                UserScoreProgressBar(userScoreProgressBarViewState = UserScoreProgressBarViewState(
                    screen_state.voteAverage,
                    textColor = Color.White,
                    radius = dimensionResource(id = R.dimen.userScoreProgressBarRadius))
                )

                Text(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.userScoreProgressBarRadius)*0.5f),
                    text = stringResource(id = R.string.user_score),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                text = screen_state.title,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White,
            )

            FavoritesButton(isFavorite = screen_state.isFavorite, onClick = {})

        }

    }
}

@Composable
fun Overview(
    screen_state:MovieDetailsViewState = movieDetailsViewState,
    fontFamily: FontFamily,
){
    Text(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.movieDetailsOverviewTitlePadding)),
        text = stringResource(id = R.string.overview),
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 20.sp,

        )

    Text(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.movieDetailsOverviewTitlePadding)),
        text = screen_state.overview,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Light,
        color = Color.Black,
        fontSize = 15.sp,
    )

}

@Composable
private fun Crew(
    crew: List<Crewman>,
    modifier: Modifier = Modifier,
) {
    val crewToDisplay = crew
        .take(NUMBER_OF_CREW_TO_DISPLAY)
        .chunked(NUMBER_OF_CREW_TO_DISPLAY / 2)
        .let {
            val firstRow = it.firstOrNull() ?: emptyList()
            val secondRow = it.getOrNull(1) ?: emptyList()

            firstRow.mapIndexed { index, crewman ->
                crewman to secondRow.getOrNull(index)
            }
        }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        crewToDisplay.forEachIndexed { index, crewmanPair ->
            Column(
                Modifier
                    .weight(1f)
                    .padding(horizontal = if (index == 1) 8.dp else 0.dp)
            ) {
                CrewItem(
                    crewItemViewState = CrewItemViewState(name = crewmanPair.first.name, job = crewmanPair.first.job)
                )

                crewmanPair.second?.let {
                    Spacer(Modifier.height(26.dp))
                    CrewItem(
                        crewItemViewState = CrewItemViewState(name = crewmanPair.second!!.name, job = crewmanPair.second!!.job)
                    )
                }
            }
        }
    }

}

@Composable
fun Cast(
    cast: List<Actor>,
    castModifier: Modifier = Modifier,
){
    val fontFamily = FontFamily(
        Font(R.font.titilliumweb_bold, FontWeight.Bold),
        Font(R.font.titilliumweb_light, FontWeight.Light)
    )

    Text(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.movieDetailsCastTitlePadding),top = dimensionResource(id = R.dimen.movieDetailsCastTopTitlePadding)),
            text = stringResource(id = R.string.topBilledCast),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp,
        )


        LazyRow(
            modifier = castModifier,
        ) {
            items(
                items = cast,
                key = {actor ->  actor.id},
            ){ actor ->
                    ActorCard(
                        actorCardViewState = ActorCardViewState(imageUrl = actor.imageUrl!!, name = actor.name, character = actor.character),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = MaterialTheme.spacing.small)
                            .size(
                                width = dimensionResource(id = R.dimen.cast_member_card_width),
                                height = dimensionResource(id = R.dimen.cast_member_card_height)
                            )
                    )
            }

        }


}


@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreen() {
    MovieDetailsScreen()
}
