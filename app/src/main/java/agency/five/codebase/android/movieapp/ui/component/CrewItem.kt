package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

data class CrewItemViewState(
    val name: String,
    val job: String,
)

@Composable
fun CrewItem(
    crewItemViewState: CrewItemViewState,
) {

    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.crewItemPadding))
    ) {
        val fontFamily = FontFamily(
            Font(R.font.titilliumweb_bold, FontWeight.Bold),
            Font(R.font.titilliumweb_light, FontWeight.Light)
        )

        Text(
            text = crewItemViewState.name,
            fontFamily = fontFamily,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp,

            )

        Text(
            text = crewItemViewState.job,
            fontFamily = fontFamily,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            fontSize = 15.sp,
        )
    }
}

@Preview
@Composable
private fun CrewItemPreview() {
    val mockCrewman = MoviesMock.getCrewman()
    CrewItem(
        crewItemViewState = CrewItemViewState(name = mockCrewman.name, job = mockCrewman.job),
    )


}
