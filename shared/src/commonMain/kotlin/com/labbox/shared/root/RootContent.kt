package com.labbox.shared.root


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade()), // 화면 전환 시 fade-in/out 애니메이션 적용
    ) {
        // stack에서 현재 활성화된 자식(it.instance)에 따라 UI를 렌더링.
        when (val child = it.instance) {
            is RootComponent.Child.ListChild -> ListContent(component = child.component)
            is RootComponent.Child.DetailsChild -> DetailsContent(component = child.component)
        }
    }
}