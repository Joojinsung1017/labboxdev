package com.labbox.shared.counter

/**
 * [학습 순서 1] - 상태(State) 이해하기
 * 
 * Counter 화면의 상태를 나타내는 데이터 클래스
 * MVI 패턴에서 State는 UI를 그리는 데 필요한 모든 정보를 담고 있어야하며,
 * 일반적으로 불변(immutable)로 유지
 * 
 * 학습 포인트:
 * 1. Decompose에서는 상태를 불변(immutable) 데이터 클래스로 정의합니다.
 * 2. 상태는 UI를 그리는 데 필요한 모든 데이터를 포함해야 합니다.
 * 3. 여기서는 단순히 카운터 값(count)만 있지만, 실제 앱에서는 로딩 상태, 오류 메시지 등 
 *    다양한 UI 상태 정보가 포함될 수 있습니다.
 * 4. 기본값(default value)을 설정하여 초기 상태를 정의합니다.
 * */
data class CounterState(
    val count : Int = 0
)
