package com.nikhil.gweilandtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikhil.gweilandtask.ui.screen.BottomNavigationBar
import com.nikhil.gweilandtask.ui.screen.Exchanges
import com.nikhil.gweilandtask.ui.theme.GweilandTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GweilandTaskTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GweilandApp()
                }
            }
        }
    }
}

@Composable
fun GweilandApp() {
    Box(modifier = Modifier.fillMaxSize()) {
        Exchanges()
        BottomNavigationBar(
            modifier = Modifier.padding(13.dp).align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GweilandTaskTheme {
        GweilandApp()
    }
}