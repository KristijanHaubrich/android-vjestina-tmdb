package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.ui.home.HomeScreen
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsScreen
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme{
                //MovieDetailsScreen()
                //FavoritesScreen()
                HomeScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
