package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
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
    modifier: Modifier,
) {

    Column(
        modifier = modifier
    ) {
        val fontFamily = FontFamily(
            Font(R.font.titilliumweb_bold, FontWeight.Bold),
            Font(R.font.titilliumweb_light, FontWeight.Light)
        )

        Text(
            modifier = Modifier.padding(top = R.dimen.crewItemTextNamePaddingTop.dp, start = R.dimen.crewItemTextNamePaddingStart.dp, bottom = R.dimen.crewItemTextNamePaddingBottom.dp),
            text = crewItemViewState.name,
            textAlign = TextAlign.Left,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Text(
            modifier = Modifier.padding(top = R.dimen.crewItemTextJobPaddingTop.dp, start = R.dimen.crewItemTextJobPaddingStart.dp, bottom = R.dimen.crewItemTextJobPaddingBottom.dp),
            text = crewItemViewState.job,
            textAlign = TextAlign.Left,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp

        )
    }
}

@Preview
@Composable
private fun CrewItemPreview() {
    val mockCrewman = MoviesMock.getCrewman()
    CrewItem(
        crewItemViewState = CrewItemViewState(name = mockCrewman.name, job = mockCrewman.job),
        modifier = Modifier.fillMaxSize()
    )


}
