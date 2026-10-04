# Senior SWE Interview Preparation

A six-month preparation workspace for a senior engineer moving from TypeScript
and frontend systems toward Java, algorithms, and distributed system design.

## Start here

1. Read the [24-week roadmap](docs/SENIOR_SWE_24_WEEK_ROADMAP.md) for the topic
   sequence and weekly outcomes.
2. Read the [coaching profile](INTERVIEW_COACHING_PROFILE.md) and
   [operating system](INTERVIEW_PREPARATION_OS.md) for a sustainable cadence.
3. Update [PROGRESS.md](PROGRESS.md) after each week so study progress travels
   with the repository.
4. Use the [90-day question bank](SWE_90_DAY_QUESTION_BANK.md) as an
   optional accelerated problem reference, not as a second schedule.

## Repository map

- `docs/SENIOR_SWE_24_WEEK_ROADMAP.md` — primary six-month plan
- `INTERVIEW_COACHING_PROFILE.md` — personal constraints and coaching
  preferences
- `INTERVIEW_PREPARATION_OS.md` — weekly process and readiness review
- `PROGRESS.md` — durable weekly progress and error log
- `SWE_90_DAY_QUESTION_BANK.md` — optional condensed question bank
- `docs/TS_TO_JAVA_CHEAT_SHEET.md` — TypeScript-to-Java reference
- `problems/` — practice implementations in Java and TypeScript
- `run_java.sh` — compile and run a standalone Java exercise

## Local setup

Install a JDK (17 or newer) and make `java` and `javac` available on `PATH`.
Then run a Java exercise from the repository root:

```sh
./run_java.sh problems/TwoSumSorted.java
```

The exercises use Java `assert`; the runner enables assertions. Generated class
files are ignored by Git.

## Progress across devices

Commit `PROGRESS.md` updates along with code or documentation changes, then push
the repository to a private or public GitHub repository of your choice. Avoid
committing credentials, employer-confidential material, or personal data that
should not be shared with collaborators.
