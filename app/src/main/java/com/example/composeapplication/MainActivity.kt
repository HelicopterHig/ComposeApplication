package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.ui.theme.ComposeAppTheme
import com.example.composeapplication.ui.theme.errorInputBackground
import com.example.composeapplication.ui.theme.inputBackground
import com.example.composeapplication.ui.theme.onInputBackground


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, end = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Greeting()

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(
    placeholder: String = "●●●●●●",
    isError: Boolean = false
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp),
        //horizontalAlignment = Alignment.CenterHorizontally,
        // verticalArrangement = Arrangement.Center
    ) {
        var text by remember { mutableStateOf(" Type here...") }
        BasicTextField(
            value = text,
            visualTransformation =
            if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(mask = '●'),
            onValueChange = { newText ->
                text = newText
            },
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isError) MaterialTheme.colors.errorInputBackground else MaterialTheme.colors.inputBackground)
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        Modifier
                            .weight(1f)
                            .padding(start = 12.dp, bottom = 2.dp),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        innerTextField()

                        if (text.isEmpty()) {
                            Text(
                                modifier = Modifier.padding(bottom = 2.dp),
                                text = placeholder,
                                color = MaterialTheme.colors.onInputBackground.copy(.35f),
                                fontSize = 16.sp,
                            )
                        }
                    }

                    Spacer(Modifier.width(16.dp))

                    IconButton(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onClick = {
                            passwordVisibility = !passwordVisibility
                        }
                    ) {
                        AnimatedVisibility(
                            visible = passwordVisibility,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_hide),
                                "Show Password"
                            )
                        }

                        AnimatedVisibility(
                            visible = !passwordVisibility,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_show),
                                "Hide Password"
                            )
                        }
                    }
                }
            }
        )
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
        Greeting()
    }
}