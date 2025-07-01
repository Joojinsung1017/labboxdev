package com.labbox.shared.root

import com.arkivanov.decompose.value.Value

interface ListComponent {
    // UI에 보여줄 데이터 모델. 여기서는 아이템 목록을 가지고 있어.
    val model: Value<Model>

    // 아이템이 클릭되었을 때 호출될 함수.
    fun onItemClicked(item: String)

    data class Model(
        val items: List<String>,
    )
}