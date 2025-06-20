package com.labbox.shared.counter

import kotlinx.coroutines.flow.StateFlow

/**
 * Counter 기능을 위한 Decompose 컴포넌트 인터페이스
 * 이 인터페이스는 UI 레이어가 Counter 컴포넌트와 상호작용하는 방법을 정의
 *
 * MVI 패턴 관점에서 보면
 * - `state` : UI가 구독할 상태(State)를 제공
 * - `onIntent()` : UI로부터 사용자 의도(Intent)를 전달받는 메서드
 * */
interface CounterComponent {
    /**
     * UI가 관찰(observe)할 현재 Counter의 상태
     * */
    val state: StateFlow<CounterState>

    /**
     * UI로부터 사용자 의도(Intent)를 전달받아서 처리
     * @param intent 사용자가 발생시킨 의도 (예: Increment, Decrement)
     * */
    fun onIntent(intent: CounterIntent)
}