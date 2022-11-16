package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Gray600
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState,
)

sealed class MovieCategoryLabelTextViewState {
    data class Text(val text: String) : MovieCategoryLabelTextViewState()
    data class TextRes(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()
}

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelUiState: MovieCategoryLabelViewState,
    onItemClick: (MovieCategoryLabelViewState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onItemClick(movieCategoryLabelUiState) }
            .width(IntrinsicSize.Max),
    ) {
        Text(
            text = when (movieCategoryLabelUiState.categoryText) {
                is MovieCategoryLabelTextViewState.Text -> movieCategoryLabelUiState.categoryText.text
                is MovieCategoryLabelTextViewState.TextRes -> stringResource(movieCategoryLabelUiState.categoryText.textRes)
            },
            color = if (movieCategoryLabelUiState.isSelected) Color.Black else Gray600,
            fontSize = 16.sp,
            fontWeight = if (movieCategoryLabelUiState.isSelected) FontWeight.Bold else FontWeight.Normal,
            maxLines = 1
        )

        Box(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.extraSmall)
                .background(if (movieCategoryLabelUiState.isSelected) Color.Black else Color.Transparent)
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.favorite_button_size))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCategoryLabelPreview() {
    var movieCategoryLabelUiState by remember {
        mutableStateOf(
            MovieCategoryLabelViewState(
                itemId = 0,
                isSelected = true,
                categoryText = MovieCategoryLabelTextViewState.Text("Movies")
            )
        )
    }

    MovieCategoryLabel(
        movieCategoryLabelUiState = movieCategoryLabelUiState,
        onItemClick = {
            val isSelected = !movieCategoryLabelUiState.isSelected
            movieCategoryLabelUiState = movieCategoryLabelUiState.copy(isSelected = isSelected)
        },
    )
}


