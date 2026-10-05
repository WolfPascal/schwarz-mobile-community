# Schwarz Mobile Community — Blueprint & Workshop Documentation

Welcome to the **Schwarz Mobile Community** reference repository. This project serves as an internal production-ready Kotlin Multiplatform (KMP) blueprint and an interactive workshop environment for mobile developers across the Schwarz Group.

---

## 📌 Global Project Rules

1. **Strictly English**:
   - All code, packages, class/function names, variables, unit tests, comments, git commits, and documentation must be written in **English**.
2. **Modular Architecture & Feature API/Impl Pattern**:
   - The application is decoupled into granular Gradle modules divided by layers and features (`:core:*` and `:feature:*`).
   - Every feature module is strictly split into an API contract module (`:feature:<name>:api`) and a private implementation module (`:feature:<name>:impl`). Cross-feature dependencies are restricted exclusively to `:api` modules.
3. **Strict Build/Gradle Policy**:
   - Automated agents must **NEVER** run Gradle commands (`./gradlew`, `gradle`) automatically. Always ask the developer to execute builds or test runs manually.

---

## 📚 Documentation Structure

The documentation is strictly split into **Functional (Fachlich)** and **Technical (Technisch)** tracks:

### 📱 1. Functional Specifications (`functional/`)
Specifications covering user requirements, community journeys, feature design, and didactic concepts:
- [**01_app_overview.md**](./functional/01_app_overview.md): High-level app goals, target personas, and bottom navigation flow.
- [**02_news_feature.md**](./functional/02_news_feature.md): News feed requirements, article schema, categories, and interactions.
- [**03_meetings_feature.md**](./functional/03_meetings_feature.md): Monthly gatherings, upcoming meetings focus, agendas, and archive.
- [**04_workshop_concepts.md**](./functional/04_workshop_concepts.md): Didactic outline of the 3 workshop stages (Shared Logic, Shared UI, Platform Specifics).

### 🛠️ 2. Technical Specifications (`technical/`)
Specifications covering architecture, code patterns, modularization, and setup:
- [**01_modular_architecture.md**](./technical/01_modular_architecture.md): Multi-module layout (`:core:*`, `:feature:*`), dependency graph, and target platforms.
- [**02_network_and_mock_simulation.md**](./technical/02_network_and_mock_simulation.md): Ktor HTTP Client setup, MockEngine simulation, JSON data assets, and error handling.
- [**03_state_management_and_ui.md**](./technical/03_state_management_and_ui.md): Compose Multiplatform standards, Unidirectional Data Flow (UDF), and ViewModels.
- [**04_workshop_implementation_plan.md**](./technical/04_workshop_implementation_plan.md): Concrete technical steps for the workshop exercises.
- [**05_coding_guidelines.md**](./technical/05_coding_guidelines.md): Language rules, package namespaces (`com.schwarz_digits.mobilecommunity`), and naming conventions.
