package com.labbox.shared.counter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * CounterComponent의 상태를 표시하고 상호작용하는 Composable UI입니다.
 *
 * @param component UI와 로직을 연결하는 CounterComponent 인스턴스입니다.
 */
@Composable
fun CounterUi(component: CounterComponent, modifier: Modifier) { // Changed to a Composable function
    // component.state (StateFlow)를 구독하여 Compose 상태로 변환합니다.
    // state 값이 변경될 때마다 UI가 자동으로 리컴포지션됩니다.
    val state by component.state.collectAsState()

    // MaterialTheme은 앱의 최상위 수준(예: Root UI 또는 MainActivity)에서 한 번만 적용하는 것이 좋습니다.
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Count: ${state.count}", // CounterState의 count 값을 표시
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onBackground // Material 3 테마 색상 적용
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = {
                    // '감소' 버튼 클릭 시 Decrement Intent를 컴포넌트에 전달
                    component.onIntent(CounterIntent.Decrement)
                }) {
                    Text("Decrement")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    // '증가' 버튼 클릭 시 Increment Intent를 컴포넌트에 전달
                    component.onIntent(CounterIntent.Increment)
                }) {
                    Text("Increment")
                }
            }
        }
    }
}