package com.labbox.shared.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class DefaultListComponent(
    componentContext: ComponentContext,
    private val onItemSelected: (item: String) -> Unit, // RootComponent로부터 콜백 함수를 받음
) : ListComponent, ComponentContext by componentContext {

    // 100개의 더미 아이템을 가진 모델을 생성
    override val model: Value<ListComponent.Model> =
        MutableValue(ListComponent.Model(items = List(100) { "Item $it" }))

    override fun onItemClicked(item: String) {
        // 아이템이 클릭되면, 생성자에서 받아온 콜백 함수를 실행!
        onItemSelected(item)
    }
}