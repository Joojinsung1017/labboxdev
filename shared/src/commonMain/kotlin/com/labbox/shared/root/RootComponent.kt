package com.labbox.shared.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable


// 최상위 컴포넌트의 설계도
interface RootComponent {
    // ChildStack의 상태를 UI에 노출하기 위한 Value 객체.
    // UI는 이 stack을 관찰하다가, 변화가 생기면 화면을 새로 그려.
    val stack: Value<ChildStack<*, Child>>

    // iOS 같은 환경에서 뒤로가기 버튼을 네이티브 UI로 구현할 때 필요할 수 있어.
    fun onBackClicked(toIndex: Int)

    // 이 RootComponent가 가질 수 있는 자식 컴포넌트들을 정의하는 곳.
    // sealed class로 만들어서 어떤 종류의 자식들이 있는지 명확하게 알 수 있어.
    sealed class Child {
        // List 화면을 위한 자식
        class ListChild(val component: ListComponent) : Child()
        // Details 화면을 위한 자식
        class DetailsChild(val component: DetailsComponent) : Child()
    }
}

// 각 화면을 식별하고, 화면 간에 데이터를 전달하기 위한 설정 클래스.
// @Serializable 어노테이션은 Decompose가 상태를 저장하고 복원할 때 필요해.
@Serializable
sealed interface Config {
    @Serializable
    data object List : Config // 목록 화면을 위한 설정

    @Serializable
    data class Details(val item: String) : Config // 상세 화면을 위한 설정 (아이템 텍스트를 전달받음)
}