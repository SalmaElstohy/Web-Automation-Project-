# OpenCart Automation Project

This is a test automation framework built to handle functional, UI, and regression testing for an e-commerce platform. The goal was to build something stable and easy to maintain, so it uses standard design patterns to keep the tests from breaking whenever the UI updates.

---

## How It's Built

Instead of jamming everything into one place, we split the project up to keep it clean:
*   **Page Object Model (POM):** All the page elements and UI actions live in their own classes. The actual test scripts just call these actions, which makes updating tests much easier if a locator changes.
*   **Explicit Waits:** To fix the usual timing issues and flakiness with web apps, the framework uses explicit waits uniformly rather than hardcoded sleeps.
*   **Test Base:** Driver initialization, configuration properties, and cleanup are managed in a central base class so individual tests don't have to handle setup logic.
*   **Extent Reports:** Every run automatically generates an interactive HTML report so you can visually track pass/fail history and see exactly what went wrong if a test fails.

---

## Tech Stack
*   **Language:** Java
*   **Tools:** Selenium WebDriver & TestNG
*   **Build System:** Maven
*   **Reporting:** Extent Reports
