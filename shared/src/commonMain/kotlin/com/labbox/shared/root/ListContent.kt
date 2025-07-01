package com.labbox.shared.root

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun ListContent(component: ListComponent, modifier: Modifier = Modifier) {
    // ListComponent의 model을 구독해서 UI 상태로 사용.
    val model by component.model.subscribeAsState()

    LazyColumn(modifier = modifier) {
        items(model.items) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { component.onItemClicked(item) } // 아이템 클릭 시 컴포넌트의 함수 호출
                    .padding(16.dp)
            )
        }
    }
}