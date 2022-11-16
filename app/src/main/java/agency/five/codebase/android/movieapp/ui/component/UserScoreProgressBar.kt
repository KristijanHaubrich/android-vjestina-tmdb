package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.math.RoundingMode

data class UserScoreProgressBarViewState(
    val rating: Float,
    val lowerBound: Float = 0f,
    val upperBound: Float = 10f,
    val fontSize: TextUnit = 20.sp,
    val radius: Dp = 40.dp,
    val indicatorcColor: Color = Color.Green,
    val textColor: Color = Color.Black,
    val strokeWidth: Dp = 5.dp,
    val animDurationInMilis: Int = 1000,
    val animDelayInMilis: Int = 0
)

@Composable
fun UserScoreProgressBarr(
    userScoreProgressBarViewState: UserScoreProgressBarViewState
){
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val currentRating = animateFloatAsState(
        targetValue = if (animationPlayed) userScoreProgressBarViewState.rating else 0f,
        animationSpec = tween(
            durationMillis = userScoreProgressBarViewState.animDurationInMilis,
            delayMillis = userScoreProgressBarViewState.animDelayInMilis
        )
    )

    LaunchedEffect(key1 = true){
        animationPlayed = true
    }

    Box(
        contentAlignment =  Alignment.Center,
        modifier = Modifier.size(userScoreProgressBarViewState.radius*2).padding(8.dp),
    ){
        Canvas(
            modifier = Modifier.size(userScoreProgressBarViewState.radius * 2f),
        ){
            drawArc(
                color = userScoreProgressBarViewState.indicatorcColor,
                alpha = 0.1f,
                startAngle = -180f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(userScoreProgressBarViewState.strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Canvas(
            modifier = Modifier.size(userScoreProgressBarViewState.radius * 2f)
        ){
            drawArc(
                color = userScoreProgressBarViewState.indicatorcColor,
                startAngle = -90f,
                sweepAngle = 360*((currentRating.value - userScoreProgressBarViewState.lowerBound)/(userScoreProgressBarViewState.upperBound - userScoreProgressBarViewState.lowerBound)),
                useCenter = false,
                style = Stroke(userScoreProgressBarViewState.strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = currentRating.value.toBigDecimal().setScale(1, RoundingMode.UP).toFloat().toString(),
            color = userScoreProgressBarViewState.textColor,
            fontSize = userScoreProgressBarViewState.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun CrewItemPreview() {

}
