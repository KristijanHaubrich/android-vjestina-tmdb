package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

sealed class MovieCategoryLabelTextViewState{
    data class Text(val text: String): MovieCategoryLabelTextViewState()
    data class TextRes(@StringRes val textRes: Int): MovieCategoryLabelTextViewState()
}

@Composable
fun MovieCategoryLabel(
    movieCardViewState: MovieCategoryLabelViewState,
    onItemClick: (MovieCategoryLabelTextViewState) -> Unit,
    modifier: Modifier,
){
    
}


@Composable
fun stringResLabel(@StringRes strRes:Int, isSelected: Boolean, modifier: Modifier){

    val fontFamily = FontFamily(
        Font(R.font.titilliumweb_bold, FontWeight.Bold),
        Font(R.font.titilliumweb_black, FontWeight.Black)
    )

    val fontWeight = when(isSelected){
       true -> FontWeight.Black
       false -> FontWeight.Bold
    }

    val fontColor = when(isSelected){
        true -> Color.Black
        false -> Color.Gray
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier.padding(top = R.dimen.movieCategoryLabelTextPaddingTop.dp, bottom = R.dimen.movieCategoryLabelTextPaddingBottom.dp),
            text = stringResource(id = strRes),
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            color = fontColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        if(isSelected){
            Divider( thickness = R.dimen.movieCategoryLabelDividerThickness.dp, color = Color.Black)
        }
    }
}

@Composable
fun stringLabel(text:String, isSelected: Boolean, modifier: Modifier){

    val fontFamily = FontFamily(
        Font(R.font.titilliumweb_bold, FontWeight.Bold),
        Font(R.font.titilliumweb_black, FontWeight.Black)
    )

    val fontWeight = when(isSelected){
        true -> FontWeight.Black
        false -> FontWeight.Bold
    }

    val fontColor = when(isSelected){
        true -> Color.Black
        false -> Color.Gray
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier.padding(top = R.dimen.movieCategoryLabelTextPaddingTop.dp, bottom = R.dimen.movieCategoryLabelTextPaddingBottom.dp),
            text = text,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            color = fontColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        if(isSelected){
            Divider( thickness = R.dimen.movieCategoryLabelDividerThickness.dp, color = Color.Black)
        }
    }
}

@Preview
@Composable
private fun MovieCategoryPreview() {

}

