package com.labbox.shared.counter

import kotlinx.coroutines.flow.StateFlow

/**
 * [학습 순서 3] - 컴포넌트 인터페이스 이해하기
 * 
 * Counter 기능을 위한 Decompose 컴포넌트 인터페이스
 * 이 인터페이스는 UI 레이어가 Counter 컴포넌트와 상호작용하는 방법을 정의
 *
 * MVI 패턴 관점에서 보면
 * - `state` : UI가 구독할 상태(State)를 제공
 * - `onIntent()` : UI로부터 사용자 의도(Intent)를 전달받는 메서드
 * 
 * 학습 포인트:
 * 1. Decompose에서는 기능별로 Component 인터페이스를 정의합니다.
 * 2. Component는 UI와 비즈니스 로직 사이의 계약(contract)입니다.
 * 3. 이 인터페이스는 UI가 필요로 하는 상태(state)와 UI가 호출할 수 있는 메서드를 정의합니다.
 * 4. StateFlow를 사용하여 상태 변경을 UI에 반응형으로 전달합니다.
 * 5. onIntent() 메서드를 통해 UI에서 발생한 사용자 액션을 처리합니다.
 * 6. 이 인터페이스는 플랫폼에 독립적이므로 Android, iOS, 웹 등 다양한 플랫폼에서 재사용할 수 있습니다.
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
