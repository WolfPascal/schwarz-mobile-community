# Technical Architecture: State Management & Compose Multiplatform

## 1. Unidirectional Data Flow (UDF)

Each feature screen implements UDF via the AndroidX Multiplatform ViewModel (`androidx.lifecycle.viewmodelCompose`):

```
 User Action / Event  ────────►  ViewModel.onEvent(event)
                                         │
                                         ▼
                                Execute Business Logic
                                (Calls Repository / UseCase)
                                         │
                                         ▼
 Composable UI        ◄────────  Exposes StateFlow<UiState>
```

---

## 2. State Modeling Pattern

State interfaces use sealed types for complete compile-time checking:

```kotlin
sealed interface NewsUiState {
    data object Loading : NewsUiState
    data class Success(
        val articles: List<NewsArticle>,
        val isRefreshing: Boolean = false
    ) : NewsUiState
    data class Error(val message: String) : NewsUiState
}
```

UI Events are represented as sealed interfaces:

```kotlin
sealed interface NewsUiEvent {
    data object Refresh : NewsUiEvent
    data class SelectArticle(val articleId: String) : NewsUiEvent
}
```

---

## 3. Screen Structure

Every screen is divided into:
1. **Screen Route / Container**:
   - Injects/holds the `ViewModel`.
   - Collects `uiState` via `collectAsStateWithLifecycle()`.
   - Passes states down and events up.
2. **Stateless Content Composable**:
   - Pure UI composable that accepts primitive values / state models and lambda callbacks.
   - Enables easy `@Preview` rendering without requiring mock ViewModels or complex DI setups.

---

## 4. Platform Navigation & iOS 26 Liquid Glass Architecture

Following the official JetBrains reference pattern ([Liquid Glass in a Compose Multiplatform app](https://kotlinlang.org/docs/multiplatform/ios-liquid-glass.html#migration-plan)):

- **Android Navigation**: Pure Compose Multiplatform `App()` hosting the Material 3 `NavigationBar` with full UDF navigation.
- **iOS Navigation (Native Shell)**: Native SwiftUI `TabView` with `.tabBarMinimizeBehavior(.automatic)` and `.tint(Color.accentColor)` in `ContentView.swift`. The iOS 26 system automatically applies Apple's authentic **Liquid Glass** effect (floating translucent tab bar, background vibrancy, automatic minimize on scroll).
- **Shared Screen Content**: All screen content (`NewsScreen`, `MeetingsScreen`) remains 100% shared Compose Multiplatform code, exposed to iOS via `NewsViewController()` and `MeetingsViewController()` (`ComposeUIViewController`).
