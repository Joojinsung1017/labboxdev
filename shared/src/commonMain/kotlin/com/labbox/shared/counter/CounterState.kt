package com.labbox.shared.counter

/**
 * Counter 화면의 상태를 나타내는 데이터 클래스
 * MVI 패턴에서 State는 UI를 그리는 데 필요한 모든 정보를 담고 있어야하며,
 * 일반적으로 불변(immutable)로 유지
 * */
data class CounterState(
    val count : Int = 0
)