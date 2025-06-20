package com.labbox.shared.counter


/**
 * Counter 화면에서 발생할 수 있는 사용자 의도(Intent) 또는 액션을 정의
 * MVI 패턴에서 Intent는 사용자의 입력이나  시스템 이벤트를 나타낸다.
 * Sealed Interface (또는 Sealed Class)를 사용하면 관련된 도믄 Intent를
 * 한 곳에서 관리하고, 컴파일 타임에 모든 경우를 처리하도록 강제할 수 있어 유용하다
 * */

sealed interface CounterIntent {
    /**
     * 카운터 값을 1 증가시키라는 의도
     * */
    data object Increment : CounterIntent

    /**
     * 카운터 값을 1 감소시카라는 의도
     * */
    data object Decrement : CounterIntent

}