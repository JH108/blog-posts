package me.jessehill.sliderwithpercent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.copy
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderPositions
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.jessehill.sliderwithpercent.ui.theme.SliderWithPercentTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SliderWithPercentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var percentComplete by remember { mutableFloatStateOf(0f) }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            label = {
                                Text(text = "Percent Complete")
                            },
                            value = percentComplete.toString(),
                            onValueChange = {},
                            enabled = false,
                            modifier = Modifier
                        )

                        Slider(
                            modifier = Modifier.padding(horizontal = 32.dp),
                            value = percentComplete,
                            onValueChange = {
                                percentComplete = it
                            },
                            valueRange = 0f..100f,
                            steps = 99,
                            thumb = {
                                SliderDefaults.Thumb(thumbSize = 24.dp)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderDefaults.Thumb(
    thumbSize: androidx.compose.ui.unit.Dp
) {
    val color = Color.Blue
    val size = thumbSize
    Canvas(modifier = Modifier.size(size)) {
        drawCircle(color = color, radius = size.toPx() / 2)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderDefaults.Track(
    sliderPositions: SliderPositions,
    sliderState: SliderState
) {

}

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    value: Float = 0f,
    onValueChange: (Float) -> Unit = {}
) {
    var circlePosition by remember { mutableFloatStateOf(0f) }
    val circleSize = 24.dp
    val barHeight = 8.dp
    val barColor = Color.LightGray
    val circleColor = Color.Blue
    val barStartPadding = circleSize / 2
    val barEndPadding = circleSize / 2

    // Animate circle position
    val animatedCirclePosition by animateDpAsState(
        targetValue = circlePosition.dp,
        animationSpec = tween(durationMillis = 50)
    )

    Column(modifier = modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight)
                .background(barColor),
            contentAlignment = Alignment.CenterStart
        ) {
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(barHeight)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        val newX = (circlePosition + dragAmount.x).coerceIn(0f, 100f)
                        circlePosition = newX
                        onValueChange(newX)
                    }
                }
            ) {
                val barWidth = size.width
                val circleRadiusPx = circleSize.toPx() / 2f

                drawRect(
                    color = barColor,
                    topLeft = Offset(barStartPadding.toPx(), 0f),
                    size = size.copy(width = barWidth - (barStartPadding.toPx() * 2))
                )
            }

            // Circle handle
            Box(
                modifier = Modifier
                    .padding(start = barStartPadding + animatedCirclePosition)
                    .size(circleSize)
                    .clip(CircleShape)
                    .background(circleColor)
            )
        }
        Text(text = "Value: ${value.toInt()}", modifier = Modifier.padding(top = 8.dp))
    }
}