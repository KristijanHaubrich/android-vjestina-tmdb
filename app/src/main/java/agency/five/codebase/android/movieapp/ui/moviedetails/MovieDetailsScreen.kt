package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsImpl
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.movieDetailsMapper
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.math.RoundingMode


private val movieDetailsMapper: movieDetailsMapper = MovieDetailsImpl()
val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsScreen(
    screen_state:MovieDetailsViewState = movieDetailsViewState,
){
    val fontFamily = FontFamily(
        Font(R.font.titilliumweb_bold, FontWeight.Bold),
        Font(R.font.titilliumweb_light, FontWeight.Light)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.favoritesScreenGridPadding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.favoritesScreenSpaceBetweenCards)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.favoritesScreenSpaceBetweenCards))
    ) {
        items(
            items = screen_state.crew,
            key = { crewman ->
                crewman.id
            }
        ) { crewman ->

            CrewItem(crewItemViewState = CrewItemViewState(
                name = crewman.name,
                job = crewman.job
            ))

        }
    }
}
