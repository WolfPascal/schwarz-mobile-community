# Functional Specification: Meetings & Gatherings

## 1. Feature Objective

The **Meetings** feature organizes monthly gatherings where mobile engineers across the Schwarz Group meet to exchange ideas, present lightning talks, and discuss cross-platform challenges.

---

## 2. Scope & Hierarchy

To keep the application focused and suitable for workshop participants:
1. **Upcoming Meetings (Primary Focus)**:
   - Chronological list of forthcoming community events.
   - Highlights the next scheduled meeting prominently at the top of the screen.
2. **Past Meetings (Secondary / Archive)**:
   - Historical sessions archived with topics and speaker references.
   - Kept simple to not distract from the primary workshop tasks.

---

## 3. Meeting Information Model

Each meeting item includes:
- **Title**: Theme of the gathering (e.g., *"Mobile Gathering #14: Compose Multiplatform in Production"*).
- **Date & Time**: Event date and time window (e.g., *"November 18, 2026 • 16:00 - 17:30 CET"*).
- **Meeting Format**:
  - `VIRTUAL`: Online via Google Meet.
  - `HYBRID`: On-site in Heilbronn / Neckarsulm + virtual stream via Google Meet.
  - `ONSITE`: In-person meetup.
- **Location or Link**: Room name or virtual call placeholder link.
- **Speakers**: List of colleagues presenting sessions.
- **Agenda / Topics**: Bullet points of planned talks and discussions.
- **Status Flag**: Flag indicating whether the event is upcoming or past.

---

## 4. User Interaction

- Tapping on the meeting card expands or displays detailed agenda items and speakers.
- Action button placeholder: *"Join Call"* (if virtual) or *"Add to Calendar"* (connected in Use Case 3 for platform-specific capabilities).
