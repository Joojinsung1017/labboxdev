package com.labbox.dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.arkivanov.decompose.defaultComponentContext
import com.labbox.shared.root.DefaultRootComponent
import com.labbox.shared.root.RootContent

/**
 * [학습 순서 6] - 앱 통합 이해하기
 * 
 * 이 클래스는 Android 앱에서 Decompose 컴포넌트를 초기화하고 UI와 연결하는 방법을 보여줍니다.
 * 
 * 학습 포인트:
 * 1. defaultComponentContext()를 사용하여 Android 액티비티의 생명주기와 연결된 ComponentContext를 생성합니다.
 * 2. 이 ComponentContext를 사용하여 DefaultCounterComponent를 초기화합니다.
 * 3. Jetpack Compose의 setContent 블록 내에서 CounterUi 컴포저블을 호출하여 UI를 설정합니다.
 * 4. 이렇게 하면 Android 앱의 생명주기와 Decompose 컴포넌트의 생명주기가 동기화됩니다.
 * 5. 플랫폼별 진입점(MainActivity)에서 공통 코드(CounterComponent, CounterUi)를 사용하는 방식을 보여줍니다.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Decompose 컴포넌트 생성
        val root = DefaultRootComponent(
            // 실제로는 RootComponent를 생성하겠지
            componentContext = defaultComponentContext(),
        )

        // 2. Jetpack Compose로 화면을 채우라는 명령
        setContent {
            // 3. 바로 여기에 넣는 거야!
            // 우리가 shared 모듈에 만든 공통 UI를 화면에 그리라고 지정
            RootContent(component = root)
        }
    }
}
