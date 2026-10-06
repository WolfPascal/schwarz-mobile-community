# Project Guidelines: Schwarz Mobile Community

> ⚠️ **Important Reference**:
> Please check the [`docs/`](./docs) directory for all technical and functional specifications, architectural requirements, modularization guidelines, coding conventions, and workshop use cases.

## Language Policy: Strictly English
- All code, documentation, naming, and architectural definitions must be in **English**.
- All user-facing texts (UI, strings.xml, mock data), source code, documentation, KDoc comments, commit messages, and PR descriptions must be written in **English** by default.
- No German or other languages should be used unless explicitly requested by the user.

## KtLint Execution After Each Task
- After every code modification or implementation involving Kotlin files (`.kt`), **ktlint** must always be executed:
  1. Auto-format: `/opt/homebrew/bin/ktlint -F "**/src/**/*.kt"`
  2. Verification: `/opt/homebrew/bin/ktlint "**/src/**/*.kt"`
- **Important**: Strictly follow the safety rule regarding Gradle: NEVER execute `./gradlew ktlintCheck` or `./gradlew ktlintFormat` autonomously. Always use the installed CLI tool `/opt/homebrew/bin/ktlint`.
- A task is only considered complete once ktlint passes without errors (Exit Code 0).
