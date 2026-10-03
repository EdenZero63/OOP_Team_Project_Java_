# Campus Event System

A Java console application for managing campus events, students, organizers, and registrations.

## Project Structure

```
src/
├── app/          Entry point (Main)
├── ui/           Console menu and input handling
├── model/        Domain classes (Student, Organizer, Event types, Registration)
├── service/      Business logic managers and reports
├── persistence/  File-based save/load
└── exception/    Custom exceptions
data/             Saved data files
docs/             Design documentation
```

## Build & Run

**Windows (PowerShell):**

```powershell
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java).FullName
java -cp out app.Main
```

**Mac / Linux / Git Bash:**

```bash
javac -d out $(find src -name "*.java")
java -cp out app.Main
```
