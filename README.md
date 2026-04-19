# EYouth Automation Testing Project
**Selenium + TestNG + POM + Allure**

Automated test suite for [eyouthlearning.com/ar](https://eyouthlearning.com/ar)

---

##  Project Info

| Item | Detail |
|---|---|
| **Site Under Test** | https://eyouthlearning.com/ar |
| **Language** | Java 11 |
| **Framework** | Selenium WebDriver 4.18 + TestNG 7.9 |
| **Pattern** | Page Object Model (POM) |
| **Reporting** | Allure 2.25 |
| **Build Tool** | Maven |
| **Browser** | Google Chrome (auto-managed via WebDriverManager) |

---

##  Project Structure

```
eyouth-automation/
├── pom.xml
└── src/
    └── test/
        ├── java/com/eyouth/
        │   ├── config/
        │   │   └── ConfigReader.java          # Reads config.properties
        │   ├── pages/                         # POM page classes
        │   │   ├── BasePage.java
        │   │   ├── HomePage.java
        │   │   ├── SearchPage.java
        │   │   ├── CoursesPage.java
        │   │   ├── CourseDetailsPage.java
        │   │   ├── RegistrationPage.java
        │   │   ├── LoginPage.java
        │   │   └── MyCoursesPage.java
        │   ├── tests/                         # TestNG test classes
        │   │   ├── BaseTest.java
        │   │   ├── SearchTest.java            # TC01
        │   │   ├── CourseTest.java            # TC02
        │   │   ├── RegistrationTest.java      # TC03, TC04
        │   │   ├── LoginTest.java             # TC05, TC06
        │   │   ├── EndToEndTest.java          # TC07
        │   │   ├── SocialLinksTest.java       # TC08, TC09, TC10
        │   │   └── CourseCardUITest.java      # TC11
        │   └── utils/
        │       ├── DriverManager.java         # ThreadLocal WebDriver
        │       └── WaitHelper.java            # Explicit waits (no Thread.sleep)
        └── resources/
            ├── config.properties              # ← Edit this before running
            ├── testng.xml                     # TestNG suite definition
            └── allure.properties              # Allure output path
```

---

##  Test Cases

| # | Test Case | Class |
|---|---|---|
| TC01 | Search with valid Arabic keyword | `SearchTest` |
| TC02 | Open course details page | `CourseTest` |
| TC03 | Open registration page (`/signup`) | `RegistrationTest` |
| TC04 | Register with empty username → validation error | `RegistrationTest` |
| TC05 | Login with invalid credentials → error message | `LoginTest` |
| TC06 | Login with empty fields → validation messages | `LoginTest` |
| TC07 | E2E: Login → Browse → Subscribe → Assert in My Courses | `EndToEndTest` |
| TC08 | Footer Facebook link → facebook.com | `SocialLinksTest` |
| TC09 | Footer LinkedIn link → linkedin.com | `SocialLinksTest` |
| TC10 | Footer YouTube link → youtube.com (new tab) | `SocialLinksTest` |
| TC11 | Course card UI: image, title, instructor, subscribe button | `CourseCardUITest` |

---

##  Prerequisites

| Requirement | Version |
|---|---|
| Java JDK | 11 or higher |
| Maven | 3.8+ |
| Google Chrome | Latest stable |
| Allure CLI (optional, for HTML report) | 2.x |

> **ChromeDriver** is managed automatically by WebDriverManager — no manual download needed.

---

## 🚀 Setup & Configuration

### 1. Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/automation-testing-project.git
cd automation-testing-project
```

### 2. Configure test data
Open `src/test/resources/config.properties` and update:

```properties
# Valid account for TC07 (end-to-end) — MUST be a real registered account
valid.username=your.real.email@example.com
valid.password=YourRealPassword

# Everything else can stay as-is for negative tests
```

> ⚠️ TC07 requires a **real registered account**. Create one at https://eyouthlearning.com/ar/signup first.

---

## ▶️ Running the Tests

### Run all tests
```bash
mvn clean test
```

### Run a specific test class
```bash
mvn clean test -Dtest=SearchTest
mvn clean test -Dtest=LoginTest
mvn clean test -Dtest=EndToEndTest
```

### Run in headless mode (no browser window)
Edit `config.properties`:
```properties
headless=true
```
Then run:
```bash
mvn clean test
```

---

##  Allure Report (Bonus)

### Step 1 – Run tests (generates raw results)
```bash
mvn clean test
```

### Step 2 – Generate and open the HTML report
```bash
mvn allure:serve
```
This builds the report and opens it in your browser automatically.

### OR: Generate a static report
```bash
mvn allure:report
# Report is saved to: target/site/allure-maven-plugin/index.html
```

### Installing Allure CLI (alternative)
```bash
# macOS
brew install allure

# Windows (Scoop)
scoop install allure

# Then run:
allure serve target/allure-results
```

---

## Troubleshooting

| Problem | Solution |
|---|---|
| ChromeDriver version mismatch | WebDriverManager auto-resolves this. If it fails: `mvn clean test -Dwdm.chromeDriverVersion=LATEST` |
| Arabic text not rendering in search | Ensure Chrome is launched with `--lang=ar` (already configured in DriverManager) |
| TC07 fails at login | Ensure `valid.username` and `valid.password` in config.properties are a real registered account |
| Element not found (locator stale) | The site is a React SPA; explicit waits are used throughout. Increase `explicit.wait=30` in config if on slow connection |
| `aspectjweaver` error on Maven < 3.8 | Upgrade Maven or add the dependency manually to your local repo |

---

## Design Decisions

- **No `Thread.sleep()`** — all waits use `WebDriverWait` with `ExpectedConditions`
- **ThreadLocal WebDriver** — safe for parallel execution if enabled in `testng.xml`
- **`SoftAssert` in TC11** — reports all UI element failures in one test run
- **`jsClick()` fallback** — used for footer social links which may be partially off-screen
- **Config-driven** — all URLs, credentials, timeouts are in `config.properties`; zero hardcoding in test code
- **Allure `@Step`** — every test method uses `Allure.step()` for readable report steps

---
