package com.labbox.shared.root


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun DetailsContent(component: DetailsComponent, modifier: Modifier = Modifier) {
    // DetailsComponent의 model을 구독.
    val model by component.model.subscribeAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Details for ${model.item}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = component::onFinished) { // 뒤로가기 버튼 클릭 시 컴포넌트의 함수 호출
            Text("Back")
        }
    }
}