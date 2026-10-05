# Technical Guidelines: Coding Standards & Best Practices

## 1. Language Rule: Strictly English

- **Mandatory Policy**:
  - All source code, interfaces, function names, properties, and parameters must be written in **English**.
  - All KDoc comments, markdown docs, commit messages, and PR descriptions must be written in **English**.
  - Mock data JSON keys and status codes must be in English.

---

## 2. Package Naming Conventions

Base namespace:
`com.schwarz_digits.mobilecommunity`

Module namespaces:
- `:core:model` ➔ `com.schwarz_digits.mobilecommunity.core.model`
- `:core:network` ➔ `com.schwarz_digits.mobilecommunity.core.network`
- `:core:data` ➔ `com.schwarz_digits.mobilecommunity.core.data`
- `:core:ui` ➔ `com.schwarz_digits.mobilecommunity.core.ui`
- `:feature:news:api` ➔ `com.schwarz_digits.mobilecommunity.feature.news.api`
- `:feature:news:impl` ➔ `com.schwarz_digits.mobilecommunity.feature.news.impl`
- `:feature:meetings:api` ➔ `com.schwarz_digits.mobilecommunity.feature.meetings.api`
- `:feature:meetings:impl` ➔ `com.schwarz_digits.mobilecommunity.feature.meetings.impl`
- `:shared` ➔ `com.schwarz_digits.mobilecommunity`

---

## 3. API & Implementation Module Guidelines

1. **Visibilities**:
   - In `:feature:*:api`, all contracts and navigation routes must be public.
   - In `:feature:*:impl`, classes should be `internal` wherever possible so they cannot be accessed outside the module.
2. **Dependencies**:
   - Cross-feature dependencies must exclusively target `:api` modules.
   - Example: `:feature:news:impl` may depend on `:feature:meetings:api`.
   - Never declare an `implementation(project(":feature:...:impl"))` dependency from one feature to another.

---

## 4. Immutability & Concurrency

- Use immutable properties (`val`) across all data classes.
- Expose read-only `StateFlow<T>` from ViewModels; maintain a private `MutableStateFlow<T>`.
- Use Dispatchers carefully: `Dispatchers.Default` for CPU-intensive JSON parsing, `Dispatchers.Main` for UI emissions.

---

## 5. Build Safety Policy

- Automated coding agents must **NEVER** execute Gradle commands (`./gradlew`, `gradle`) without explicit user permission.
- Always provide the exact Gradle command to the developer so they can execute it manually in their local terminal or IDE.

---

## 6. Code Style & KtLint Verification

- **Mandatory Policy**:
  - After completing any task involving Kotlin files, ktlint must be executed:
    - Format: `/opt/homebrew/bin/ktlint -F "**/src/**/*.kt"`
    - Check: `/opt/homebrew/bin/ktlint "**/src/**/*.kt"`
  - Always respect the Build Safety Policy: use the local CLI binary rather than `./gradlew`.
  - A task is only complete when ktlint passes cleanly with exit code 0.

