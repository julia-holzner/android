package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeImageAndText(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeImageAndText(modifier: Modifier = Modifier) {
    val handler: LemonStateHandler = remember { LemonStateHandler() }
    val currentState = handler.currentLemonState

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { handler.getNextState() },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(Color.Cyan)
        ) {
            Image(
                painter = painterResource(currentState.image),
                contentDescription = stringResource(currentState.contentDescription)
            )
        }
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Text(
            text = stringResource(currentState.text),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeImageAndText()
    }
}

class LemonStateHandler {

    var currentLemonState by mutableStateOf(LemonState.LEMONTREE)
        private set

    // Zustand für den Squeeze-Zyklus
    private var squeezeCount = 0
    private var requiredSqueezes = 0

    fun getNextState() {
        currentLemonState = when (currentLemonState) {
            LemonState.LEMONTREE -> {
                requiredSqueezes = (1..4).random()
                squeezeCount = 0
                LemonState.LEMON
            }

            LemonState.LEMON -> {
                squeezeCount++
                if (squeezeCount >= requiredSqueezes) {
                    LemonState.LEMONADEGLASS
                } else {
                    LemonState.LEMON // bleib im selben State
                }
            }

            LemonState.LEMONADEGLASS -> LemonState.EMPTYGLASS
            LemonState.EMPTYGLASS -> LemonState.LEMONTREE
        }
    }
}


enum class LemonState(val text: Int, val contentDescription: Int, val image: Int) {
    LEMONTREE(
        text = R.string.lemon_tree_text,
        contentDescription = R.string.lemon_tree_content_description,
        image = R.drawable.lemon_tree
    ),
    LEMON(
        text = R.string.lemon_text,
        contentDescription = R.string.lemon_content_description,
        image = R.drawable.lemon_squeeze
    ),
    LEMONADEGLASS(
        text = R.string.drink_lemonade_text,
        contentDescription = R.string.glass_of_lemonade_content_description,
        image = R.drawable.lemon_drink
    ),
    EMPTYGLASS(
        text = R.string.empty_glass_text,
        contentDescription = R.string.empty_glass_content_description,
        image = R.drawable.lemon_restart
    );
}

