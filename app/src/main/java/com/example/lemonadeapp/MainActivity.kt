package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.contentColorFor(Color.Gray)
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    image = R.drawable.lemon_tree,
                    lemonText = R.string.lemon_tree_string,
                    lemonContentDescription = R.string.lemon_tree_content_description,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }
            2 -> {
                LemonTextAndImage(
                    image = R.drawable.lemon_squeeze,
                    lemonText = R.string.lemon_string,
                    lemonContentDescription = R.string.lemon_content_description,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }
            3 -> {
                LemonTextAndImage(
                    image = R.drawable.lemon_drink,
                    lemonText = R.string.lemonade_string,
                    lemonContentDescription = R.string.lemonade_content_description,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }
            4 -> {
                LemonTextAndImage(
                    image = R.drawable.lemon_restart,
                    lemonText = R.string.empty_glass_string,
                    lemonContentDescription = R.string.empty_glass_content_description,
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    modifier: Modifier = Modifier,
    image: Int,
    lemonText: Int,
    lemonContentDescription: Int,
    onImageClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = lemonContentDescription),
            modifier = Modifier.wrapContentSize()
                .clickable(onClick = onImageClick)
                .padding(16.dp)
                .background(Color(0xFFF7BE5D9))
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = lemonText),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyAppPreview() {
    LemonadeAppTheme {
        LemonadeApp()
    }
}
