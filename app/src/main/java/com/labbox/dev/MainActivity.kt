package com.labbox.dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.labbox.dev.ui.theme.DevTheme

import com.arkivanov.decompose.defaultComponentContext
import com.labbox.shared.counter.DefaultCounterComponent
import com.labbox.shared.counter.CounterUi

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

        // Decompose의 ComponentContext 생성 - Android 액티비티의 생명주기와 연결됨
        val componentContext = defaultComponentContext()

        // ComponentContext를 사용하여 DefaultCounterComponent 초기화
        val counterComponent = DefaultCounterComponent(componentContext)

        enableEdgeToEdge()
        setContent {
            DevTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 공통 코드로 작성된 CounterUi 컴포저블 호출
                    CounterUi(
                        component = counterComponent,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
