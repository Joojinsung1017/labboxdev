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

class DefaultCounterComponent(
    componentContext: ComponentContext,
) : CounterComponent, ComponentContext by componentContext {

    private val scope = coroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    // MVI 로직을 위한 상태 관리
    private val _state = MutableStateFlow(CounterState())

    override val state: StateFlow<CounterState> = _state.asStateFlow()

    init {
        println("DefaultCounterComponent 초기화 완료! (with Essenty lifecycle.coroutineScope)")
    }

    override fun onIntent(intent: CounterIntent) {
        scope.launch {
            when (intent) {
                CounterIntent.Increment -> {
                    _state.update { currentState ->
                        currentState.copy(count = currentState.count + 1)
                    }
                    println("Intent: Increment, New count: ${_state.value.count}")
                }

                is CounterIntent.Decrement -> {
                    _state.update { currentState ->
                        val newCount = if (currentState.count > 0) currentState.count - 1 else 0
                        currentState.copy(count = newCount)
                    }
                    println("Intent: Decrement, New count: ${_state.value.count}")

                }

            }

        }
    }
}