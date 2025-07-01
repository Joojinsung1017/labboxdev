package com.labbox.shared.root

import com.arkivanov.decompose.ComponentContext


import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.serializer

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext { // 'by componentContext'로 기능 위임

    // 화면 전환을 담당할 네비게이션 객체를 만들어.
    private val navigation = StackNavigation<Config>()

    // Decompose의 childStack 함수를 사용해서 네비게이션 스택을 생성!
    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation, // 위에서 만든 네비게이션 객체를 source로 지정
            serializer = serializer<Config>(),  // 상 저장을 위한 직렬화기. Config에서 자동으로 만들어줘.
            initialConfiguration = Config.List, // 앱이 처음 켜졌을 때 보여줄 초기 화면
            handleBackButton = false, // 안드로이드의 뒤로가기 버튼을 자동으로 처리해주는 설정
            childFactory = ::child, // 각 Config에 맞는 자식 컴포넌트를 생성하는 팩토리 함수 지정
        )

    // Config를 기반으로 실제 자식 컴포넌트 인스턴스를 만드는 함수.
    // childStack의 핵심 로직 중 하나야.
    private fun child(config: Config, context: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.List -> RootComponent.Child.ListChild(listComponent(context))
            is Config.Details -> RootComponent.Child.DetailsChild(detailsComponent(context, config))
        }

    // ListComponent 인스턴스를 생성하는 팩토리 함수.
    // 의존성이나 콜백을 여기서 주입해.
    private fun listComponent(context: ComponentContext): ListComponent =
        DefaultListComponent(
            componentContext = context,
            // '아이템이 선택되면' 뭘 할지 여기서 정의해서 넘겨주는 거야.
            onItemSelected = { item: String ->
                // Details 화면으로 이동하라는 명령! item 데이터를 함께 전달.
                navigation.push(Config.Details(item = item))
            }
        )

    // DetailsComponent 인스턴스를 생성하는 팩토리 함수.
    private fun detailsComponent(context: ComponentContext, config: Config.Details): DetailsComponent =
        DefaultDetailsComponent(
            componentContext = context,
            item = config.item, // Config에서 전달받은 아이템 데이터를 넘겨줘.
            // '뒤로가기가 끝나면' 뭘 할지 여기서 정의. navigation.pop() 함수 자체를 넘겨줬어.
            onFinished = navigation::pop
        )

    override fun onBackClicked(toIndex: Int) {
        // iOS용 뒤로가기 로직 (여기서는 다루지 않음)
    }
}