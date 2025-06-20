package com.labbox.dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.labbox.dev.ui.theme.DevTheme

import com.arkivanov.decompose.defaultComponentContext
import com.labbox.shared.counter.DefaultCounterComponent
import com.labbox.shared.counter.CounterUi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val componentContext = defaultComponentContext()
        val counterComponent = DefaultCounterComponent(componentContext)

        enableEdgeToEdge()
        setContent {
            DevTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterUi(
                        component = counterComponent,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}