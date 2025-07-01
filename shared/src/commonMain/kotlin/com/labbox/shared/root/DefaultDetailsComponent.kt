package com.labbox.shared.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class DefaultDetailsComponent(
    componentContext: ComponentContext,
    item: String, // RootComponent로부터 아이템 데이터를 받음
    private val onFinished: () -> Unit, // RootComponent로부터 콜백 함수를 받음
) : DetailsComponent, ComponentContext by componentContext {

    // 전달받은 아이템으로 모델을 생성
    override val model: Value<DetailsComponent.Model> = MutableValue(DetailsComponent.Model(item = item))

    override fun onFinished() {
        // 뒤로가기 버튼이 눌리면, 받아온 콜백 함수를 실행!

        this.onFinished.invoke()
    }
}