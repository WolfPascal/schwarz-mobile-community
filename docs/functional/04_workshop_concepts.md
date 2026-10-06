# Functional Specification: Workshop Concepts & Didactic Design

## 1. Educational Vision

The workshop is designed for developers who already have mobile development background (Android, iOS, or general mobile) and want to understand how Kotlin Multiplatform (KMP) operates in enterprise environments.

The workshop is divided into three pedagogical stages, moving from pure data sharing to UI sharing and finally to native platform interop.

---

## 2. The Three Progressive Stages

```
   ┌────────────────────────────────────────────────────────┐
   │ Stage 1: Shared Logic & Networking                     │
   │ "Write once, run anywhere business & data logic"       │
   └───────────────────────────┬────────────────────────────┘
                               │
                               ▼
   ┌────────────────────────────────────────────────────────┐
   │ Stage 2: Shared UI (Compose Multiplatform)             │
   │ "Declarative UI shared across iOS & Android"           │
   └───────────────────────────┬────────────────────────────┘
                               │
                               ▼
   ┌────────────────────────────────────────────────────────┐
   │ Stage 3: Platform Specifics (expect/actual)            │
   │ "Bridge to native SDKs when platform power is needed"  │
   └────────────────────────────────────────────────────────┘
```

### Stage 1: Shared Logic & Networking
- **Goal**: Dispel the misconception that KMP requires sharing everything or compromises platform UI.
- **Storyline**: The News UI is already designed and polished. Participants connect the mock Ktor network client, implement repository mapping, and feed data into the shared ViewModel.
- **Takeaway**: Business logic, networking, and models are 100% shared without duplicating API consumption between Swift and Kotlin.

### Stage 2: Shared UI with Compose Multiplatform
- **Goal**: Experience how Compose Multiplatform renders consistently on both Android and iOS.
- **Storyline**: The data layer for Meetings is already available. Participants build the `MeetingsScreen` and its custom card components using Compose Multiplatform.
- **Takeaway**: How to build declarative, platform-agnostic UI with standard Material 3 components.

### Stage 3: Platform-Specific Adaptations
- **Goal**: Master escaping the shared abstraction when native platform APIs or behaviors are required.
- **Storyline**: Participants implement a platform-specific feature (e.g. sharing or calendar export) using `expect` / `actual` or platform interfaces.
- **Takeaway**: KMP does not limit platform capabilities; native Android and iOS APIs are directly accessible.
