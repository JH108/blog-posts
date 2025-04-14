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
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
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
                    val percentCompleteText by remember {
                        derivedStateOf {
                            "${percentComplete.toInt()}%"
                        }
                    }
                    val interactionSource = remember { MutableInteractionSource() }

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
                            value = percentCompleteText,
                            onValueChange = {},
                            enabled = false,
                            modifier = Modifier
                        )

                        Slider(
                            modifier = Modifier.padding(horizontal = 32.dp),
                            interactionSource = interactionSource,
                            value = percentComplete,
                            onValueChange = {
                                percentComplete = it.toInt().toFloat()
                            },
                            valueRange = 0f..100f,
                            steps = 99,
                            thumb = {
                                SliderDefaults.Thumb(interactionSource, thumbSize = DpSize(12.dp, 12.dp))
                            },
                            track = {
                                SliderDefaults.Track(sliderState = it, drawTick = DrawScope::noTick)
                            }
                        )
                    }
                }
            }
        }
    }
}

fun DrawScope.noTick(offset: Offset, color: Color) {

}
