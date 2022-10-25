package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
) {

    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        val fontFamily = FontFamily(
            Font(R.font.titilliumweb_bold, FontWeight.Bold),
            Font(R.font.titilliumweb_light, FontWeight.Light)
        )

        Text(
            modifier = Modifier.padding(top = 5.dp, start = 5.dp, bottom = 0.dp),
            text = crewItemViewState.name,
            textAlign = TextAlign.Left,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Text(
            modifier = Modifier.padding(top = 0.dp, start = 5.dp, bottom = 5.dp),
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
    val crew1 = CrewItemViewState("Robert Downey JR.", "actor")
    CrewItem(crewItemViewState = crew1)
}