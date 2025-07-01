package com.labbox.shared.root

import com.arkivanov.decompose.value.Value

interface DetailsComponent {
    // List 화면에서 전달받은 아이템 텍스트를 가지고 있는 모델.
    val model: Value<Model>

    // 뒤로가기 버튼이 클릭되었을 때 호출될 함수.
    fun onFinished()

    data class Model(
        val item: String,
    )
}