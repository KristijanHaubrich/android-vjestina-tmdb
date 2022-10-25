package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    //data for testing 1.Zadaca
    val actor1 = ActorCardViewState("https://media1.popsugar-assets.com/files/thumbor/msjEO8oq7PWud7pFv_Cm6GR6E-U/0x1:2771x2772/fit-in/500x500/filters:format_auto-!!-:strip_icc-!!-/2020/03/30/878/n/1922398/eb11f12e5e825104ca01c1.02079643_/i/Robert-Downey-Jr.jpg","Robert Downey JR.", "Tony Stark/IronMan")
    val crew1 = CrewItemViewState("Robert Downey JR.", "actor")
    val movie1 = MovieCardViewState("https://i.pinimg.com/736x/8e/ff/8f/8eff8f87c3c3f4ee0086f73f86943c21.jpg")
    val movie2 = MovieCardViewState("https://i.pinimg.com/736x/8e/ff/8f/8eff8f87c3c3f4ee0086f73f86943c21.jpg",true)
    val progressBar1 = UserScoreProgressBarViewState(rating = 7.6f)
    val progressBar2 = UserScoreProgressBarViewState(rating = 3.4f)
    val Label1 = MovieCategoryLabelViewState(1,true,MovieCategoryLabelTextViewState.stringText)
    val Label2 = MovieCategoryLabelViewState(1,false,MovieCategoryLabelTextViewState.stringResourceText)

    //ui for testing 1.Zadaca
    Column() {
        Row() {
            ActorCard(actorCardViewState = actor1)
            ActorCard(actorCardViewState = actor1)
        }
        Row() {
            CrewItem(crew1)
            CrewItem(crew1)
        }
        Row() {
            MovieCard(movieCardViewState = movie1)
            MovieCard(movieCardViewState = movie2)
        }
        Row() {
            UserScoreProgressBarr(userScoreProgressBarViewState = progressBar1)
            UserScoreProgressBarr(userScoreProgressBarViewState = progressBar2)
        }
        Row(){
            MovieCategoryLabel(movieCardViewState = Label1, directText = "Movies")
            MovieCategoryLabel(movieCardViewState = Label2, strRes = R.string.movie)
        }
    }

}
