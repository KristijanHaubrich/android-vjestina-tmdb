package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
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
    object stringText: MovieCategoryLabelTextViewState()
    object stringResourceText: MovieCategoryLabelTextViewState()
}

@Composable
fun MovieCategoryLabel(
    movieCardViewState: MovieCategoryLabelViewState,
    directText:String = "",
    @StringRes strRes:Int = 0,
){

        when (movieCardViewState.categoryText) {
            MovieCategoryLabelTextViewState.stringText -> stringLabel(
                directText = directText,
                isSelected = movieCardViewState.isSelected
            )
            MovieCategoryLabelTextViewState.stringResourceText -> stringResLabel(
                strRes = strRes,
                isSelected = movieCardViewState.isSelected
            )
        }

}

@Composable
fun stringLabel(directText:String, isSelected: Boolean){

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
        Modifier.padding(5.dp).width(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
            text = directText,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            color = fontColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        if(isSelected){
            Divider( thickness = 3.dp, color = Color.Black)
        }
    }


}

@Composable
fun stringResLabel(@StringRes strRes:Int, isSelected: Boolean){

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
        Modifier.padding(5.dp).width(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
            text = stringResource(id = strRes),
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            color = fontColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        if(isSelected){
            Divider( thickness = 3.dp, color = Color.Black)
        }
    }
}

@Preview
@Composable
private fun MovieCategoryPreview() {
    val Label1 = MovieCategoryLabelViewState(1,true,MovieCategoryLabelTextViewState.stringText)
    val Label2 = MovieCategoryLabelViewState(1,false,MovieCategoryLabelTextViewState.stringResourceText)

    Row(){
        MovieCategoryLabel(movieCardViewState = Label1, directText = "Movies")
        MovieCategoryLabel(movieCardViewState = Label2, strRes = R.string.movie)
    }
}

