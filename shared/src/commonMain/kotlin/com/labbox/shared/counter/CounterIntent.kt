package com.labbox.shared.counter


/**
 * [학습 순서 2] - 의도(Intent) 이해하기
 * 
 * Counter 화면에서 발생할 수 있는 사용자 의도(Intent) 또는 액션을 정의
 * MVI 패턴에서 Intent는 사용자의 입력이나 시스템 이벤트를 나타낸다.
 * Sealed Interface (또는 Sealed Class)를 사용하면 관련된 모든 Intent를
 * 한 곳에서 관리하고, 컴파일 타임에 모든 경우를 처리하도록 강제할 수 있어 유용하다
 * 
 * 학습 포인트:
 * 1. Decompose에서는 사용자 액션을 Intent로 모델링합니다.
 * 2. sealed interface/class를 사용하여 가능한 모든 Intent를 한 곳에서 정의합니다.
 * 3. 이렇게 하면 when 표현식에서 모든 케이스를 처리하도록 컴파일러가 강제합니다.
 * 4. 각 Intent는 필요한 데이터를 포함할 수 있습니다(예: 입력된 텍스트, 선택된 항목 ID 등).
 * 5. 여기서는 단순히 증가/감소만 있지만, 실제 앱에서는 다양한 사용자 액션이 있을 수 있습니다.
 * */

sealed interface CounterIntent {
    /**
     * 카운터 값을 1 증가시키라는 의도
     * */
    data object Increment : CounterIntent

    /**
     * 카운터 값을 1 감소시키라는 의도
     * */
    data object Decrement : CounterIntent

}
