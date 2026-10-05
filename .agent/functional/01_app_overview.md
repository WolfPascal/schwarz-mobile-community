# Functional Specification: App Overview & Navigation

## 1. Purpose & Vision

The **Schwarz Mobile Community** application is designed to connect mobile engineers, architects, and product teams across the various companies of the Schwarz Group (e.g., Lidl, Kaufland, Schwarz Digits, PreZero).

It fulfills two primary roles:
1. **Internal Blueprint**: Reference implementation of modern mobile multiplatform development.
2. **Community Hub**: A central hub to share news, architectural patterns, and coordinate monthly community gatherings.

---

## 2. Information Architecture

The application has a lightweight, intuitive navigation hierarchy based on a persistent **Bottom Navigation Bar** with two main destinations:

```
┌──────────────────────────────────────────────┐
│           Top App Bar / Branding             │
├──────────────────────────────────────────────┤
│                                              │
│                 Screen Body                  │
│                                              │
│       [ News Feed ]   OR   [ Meetings ]      │
│                                              │
├──────────────────────────────────────────────┤
│     📰 News                  📅 Meetings      │
└──────────────────────────────────────────────┘
```

### Destination 1: News (`NewsScreen`)
- Displays curated technical updates, library releases, architectural guidelines, and community news.
- Used as the foundation for **Use Case 1 (Shared Logic)**: UI is pre-built so participants can focus purely on data fetching and business logic.

### Destination 2: Meetings (`MeetingsScreen`)
- Dedicated to the monthly community gatherings.
- Focuses on upcoming events with details regarding date, time, topics, and speakers.
- Used as the target for **Use Case 2 (Compose Multiplatform)**: Participants build the UI.
