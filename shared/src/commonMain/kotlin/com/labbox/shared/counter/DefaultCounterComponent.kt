package com.labbox.shared.counter

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * [학습 순서 4] - 컴포넌트 구현 이해하기
 * 
 * CounterComponent 인터페이스의 실제 구현체입니다.
 * Decompose의 ComponentContext를 상속받아 생명주기 관리 기능을 활용합니다.
 * 
 * 학습 포인트:
 * 1. ComponentContext를 생성자로 받아 Decompose 생명주기와 통합합니다.
 * 2. ComponentContext by componentContext 구문으로 인터페이스 위임(delegation)을 사용합니다.
 * 3. coroutineScope를 사용하여 컴포넌트의 생명주기에 맞는 코루틴 스코프를 생성합니다.
 * 4. MutableStateFlow로 내부 상태를 관리하고, 불변 StateFlow로 외부에 노출합니다.
 * 5. Intent 처리 로직을 when 표현식으로 구현하여 모든 Intent 케이스를 처리합니다.
 * 6. 상태 업데이트는 항상 불변(immutable)하게 copy() 메서드를 사용합니다.
 */
class DefaultCounterComponent(
    componentContext: ComponentContext,
) : CounterComponent, ComponentContext by componentContext {

    // Decompose의 coroutineScope 확장 함수를 사용하여 컴포넌트 생명주기에 맞는 코루틴 스코프 생성
    private val scope = coroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    // MVI 로직을 위한 상태 관리 - 내부적으로는 변경 가능한 MutableStateFlow 사용
    private val _state = MutableStateFlow(CounterState())

    // 외부에는 읽기 전용 StateFlow로 노출
    override val state: StateFlow<CounterState> = _state.asStateFlow()

    init {
        println("DefaultCounterComponent 초기화 완료! (with Essenty lifecycle.coroutineScope)")
    }


    // CounterComponent 인터페이스의 onIntent 메서드 구현
    override fun onIntent(intent: CounterIntent) {
        // 코루틴 스코프 내에서 Intent 처리
        scope.launch {
            // sealed interface를 when으로 처리하면 컴파일러가 모든 케이스를 처리했는지 검사
            when (intent) {
                CounterIntent.Increment -> {
                    // 상태 업데이트는 항상 불변(immutable)하게 copy()를 사용
                    _state.update { currentState ->
                        currentState.copy(count = currentState.count + 1)
                    }
                    println("Intent: Increment, New count: ${_state.value.count}")
                }

                is CounterIntent.Decrement -> {
                    _state.update { currentState ->
                        // 비즈니스 로직: 카운트가 0 미만으로 내려가지 않도록 함
                        val newCount = if (currentState.count > 0) currentState.count - 1 else 0
                        currentState.copy(count = newCount)
                    }
                    println("Intent: Decrement, New count: ${_state.value.count}")
                }
            }
        }
    }
}
