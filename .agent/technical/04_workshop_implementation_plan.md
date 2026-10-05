# Technical Implementation Plan: Workshop Exercises (API/Impl Pattern)

## 1. Plan for Use Case 1: Shared Logic & Networking

### Blueprint Preparation
1. **Module Creation**:
   - `:core:model`: Domain model `NewsArticle`.
   - `:core:network`: `KtorNewsClient`, `MockEngine`, mock JSON payload.
   - `:core:data`: `NewsRepository` interface + implementation.
   - `:core:ui`: Common themes, typography, and card components.
   - `:feature:news:api`: `NewsFeatureEntry` contract & navigation destination.
   - `:feature:news:impl`: `NewsScreen` with pre-built Compose Multiplatform UI, `NewsViewModel`, and state classes.
2. **Workshop Exercise Flow**:
   - The instructor provides the pre-built UI in `:feature:news:impl` and the API contract in `:feature:news:api`.
   - Participants write the network call, DTO mapping in `:core:data`, and ViewModel state connection in `:feature:news:impl`.
   - Verification: Running on Android and iOS renders live mock news articles.

---

## 2. Plan for Use Case 2: Compose Multiplatform UI

### Blueprint Preparation
1. **Module Creation**:
   - `:feature:meetings:api`: Public entry point and routes for Meetings.
   - `:feature:meetings:impl`: Host for the UI implementation.
   - Domain model `Meeting` in `:core:model`, mock data in `:core:network`, and repository in `:core:data`.
   - `MeetingsViewModel` is prepared and emits `MeetingsUiState`.
2. **Workshop Exercise Flow**:
   - Participants implement the UI in `:feature:meetings:impl`:
     - `MeetingsScreen.kt`
     - `MeetingCard.kt`
     - Status badges (`Virtual`, `Hybrid`, `Onsite`)
     - Upcoming vs. past meetings list rendering.
   - Wire the feature implementation into the `:feature:meetings:api` contract.
   - Verification: Both Android and iOS display the newly created Compose UI seamlessly.

---

## 3. Plan for Use Case 3: Platform-Specific Adaptations

### Blueprint Preparation & Exercise
1. Define a shared interface or `expect` declaration:
   ```kotlin
   // In commonMain (e.g. in :core:ui or :feature:meetings:impl)
   expect class PlatformShareManager {
       fun shareText(title: String, text: String)
   }
   ```
2. Implement in `androidMain`:
   - Uses Android `Intent.ACTION_SEND` with `Context`.
3. Implement in `iosMain`:
   - Uses `UIActivityViewController` from UIKit.
4. Integrate the share button into the meeting or news card.
