🏦 **ParaBank Cucumber BDD Framework**

A comprehensive **Behavior-Driven Development (BDD)** test automation framework for the ParaBank application using **Cucumber 7**, **Selenium 4**, and **Java 17**.

---

## 📋 Overview

This framework demonstrates modern test automation best practices with:
- **Cucumber/Gherkin** for readable, business-friendly test scenarios
- **Selenium WebDriver** for robust cross-browser web automation
- **Page Object Model (POM)** architecture for maintainable test code
- **Extent Reports** for comprehensive, visual test execution reports
- **Apache POI** for data-driven testing with Excel files
- **Log4j2** for detailed test logging
- **JUnit 4** for test execution and reporting

### ✨ Key Features

✅ BDD approach with Gherkin feature files  
✅ Page Object Model design pattern  
✅ Cross-browser support (Chrome, Firefox, Edge)  
✅ Local & Remote execution options  
✅ Advanced reporting with Extent Reports & PDF exports  
✅ Comprehensive logging with Log4j2  
✅ Data-driven testing capabilities  
✅ Screenshot capture on test failures  
✅ Pre-built base classes and utilities  

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 17 | Programming language |
| **Cucumber** | 7.34.3 | BDD framework |
| **Selenium WebDriver** | 4.43.0 | Browser automation |
| **JUnit** | 4.13.2 | Test runner |
| **Extent Reports** | 5.0.9 | HTML/PDF reporting |
| **Log4j2** | 2.20.0 | Logging framework |
| **Apache POI** | 5.2.3 | Excel data handling |
| **WebDriverManager** | 5.3.2 | Driver management |
| **Maven** | 3.8+ | Build & dependency management |

---

## 📁 Project Structure

```
ParaBank_CucumberBDD_Framework/
├── featureFile/                    # Gherkin feature files
│   └── Registration.feature        # User registration scenarios
├── src/test/java/
│   ├── Factory/
│   │   └── BaseClass.java          # WebDriver initialization & utilities
│   ├── PageObjects/
│   │   ├── DriverBasePage.java     # Base page class with PageFactory setup
│   │   └── RegisterPage.java       # Registration page object
│   ├── StepDefinitions/
│   │   ├── Hooks.java              # Before/After step hooks
│   │   └── Registration.java       # Registration step definitions
│   ├── TestRunner/
│   │   └── TestRun.java            # JUnit test runner with Cucumber options
│   └── utilities/                  # Utility classes (future expansion)
├── src/test/resources/
│   ├── config.properties           # Test configuration (URL, credentials, etc.)
│   ├── log4j2.xml                  # Log4j2 configuration
│   └── extent.properties           # Extent Report configuration
├── target/                         # Build artifacts & test reports
├── testData/                       # Test data files (Excel, JSON, etc.)
├── testOutput/                     # Test execution output files
├── pom.xml                         # Maven configuration & dependencies
└── README.md                       # This file
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 17+** (JDK installed and added to PATH)
- **Maven 3.8+** (for build & dependency management)
- **Chrome/Firefox/Edge** browser (for test execution)
- **Git** (to clone the repository)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/ParaBank_CucumberBDD_Framework.git
   cd ParaBank_CucumberBDD_Framework
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Configure test settings** (optional)
   - Edit `src/test/resources/config.properties` to customize:
     - `appURL`: Target application URL
     - `browser`: Chrome, Firefox, or Edge
     - `execution_env`: local or remote
     - Test data (firstname, lastname, credentials, etc.)

---

## ▶️ Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Feature File
```bash
mvn test -Dcucumber.features=featureFile/Registration.feature
```

### Run Tests with Specific Tags
```bash
mvn test -Dcucumber.filter.tags="@regression"
```

### Run Tests from IDE
- **IntelliJ IDEA**: Right-click `TestRun.java` → Run
- **Eclipse**: Right-click test runner class → Run As → JUnit Test

---

## 📊 Test Reports

### HTML Report
After test execution, view the comprehensive HTML report:
```
target/cucumber-reports.html
```

### PDF Report (via Extent Reports)
PDF reports are generated in:
```
target/extent-reports/
```

### Console Output
Test execution logs are printed to console and also saved in `target/` directory.

---

## 📝 Writing Tests

### Example: Adding a New Scenario

1. **Add Feature File** (`featureFile/NewFeature.feature`):
   ```gherkin
   Feature: User Login
     @smoke @sanity
     Scenario: Successful login with valid credentials
       Given I navigate to the login page
       When I enter valid username and password
       And I click the login button
       Then I should see the dashboard
   ```

2. **Create Page Object** (`src/test/java/PageObjects/LoginPage.java`):
   ```java
   public class LoginPage extends DriverBasePage {
       @FindBy(id = "username")
       WebElement usernameField;
       
       @FindBy(id = "password")
       WebElement passwordField;
       
       public void enterCredentials(String username, String password) {
           usernameField.sendKeys(username);
           passwordField.sendKeys(password);
       }
   }
   ```

3. **Implement Step Definitions** (`src/test/java/StepDefinitions/LoginSteps.java`):
   ```java
   public class LoginSteps {
       private LoginPage loginPage;
       
       @Given("I navigate to the login page")
       public void navigate_to_login_page() throws IOException {
           WebDriver driver = BaseClass.getDriver();
           loginPage = new LoginPage(driver);
           // navigation logic
       }
   }
   ```

---

## 🔧 Configuration

### config.properties
```properties
execution_env=local                               # local or remote
browser=chrome                                    # chrome, firefox, edge
os=windows                                        # windows, mac, linux
appURL=https://parabank.parasoft.com/parabank/   # Target application
firstname=Dharma                                  # Test data
...
```

### Cucumber Options (TestRun.java)
```java
@CucumberOptions(
    features = "featureFile",                    # Feature file location
    glue = "StepDefinitions",                    # Step definition packages
    plugin = {"pretty", "html:target/..."},      # Report formats
    monochrome = true                            # Readable console output
)
```

---

## 🎯 Best Practices Implemented

✅ **Page Object Model** - Reduces maintenance & increases reusability  
✅ **Separation of Concerns** - Feature files, steps, pages kept separate  
✅ **Explicit Waits** - More reliable than implicit waits  
✅ **Base Classes** - DRY principle with shared WebDriver & utilities  
✅ **Configuration Management** - Externalized test data  
✅ **Logging** - Comprehensive logs for debugging  
✅ **Screenshots on Failure** - Embedded in Extent Reports  
✅ **Package Structure** - Clean, organized module layout  

---

## 🐛 Troubleshooting

### WebDriver Not Found
```bash
# Ensure WebDriverManager is configured and updates drivers automatically
# Or manually download & add to PATH:
# https://chromedriver.chromium.org/
```

### Tests Cannot Find Elements
- Verify element locators (XPath, CSS selectors) in Page Objects
- Use explicit waits: `WebDriverWait` with `ExpectedConditions`
- Check if elements are in iframes or require page scrolling

### Configuration Not Loaded
- Ensure `config.properties` is in `src/test/resources/`
- Check property key names match exactly (case-sensitive)

### Report Not Generated
- Verify Extent Report plugins are in `pom.xml`
- Check write permissions on `target/` directory

---

## 📖 Additional Resources

- **Selenium Documentation**: https://www.selenium.dev/documentation/
- **Cucumber Documentation**: https://cucumber.io/docs/gherkin/
- **Page Object Model**: https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/
- **Extent Reports**: https://www.extentreports.com/
- **Java Best Practices**: https://www.oracle.com/java/technologies/javase/codeconventions-introduction.html

---

## 🤝 Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add YourFeature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request


---

## 👤 Author
 
🔗 [GitHub](https://github.com/DharmarajKarthik) | [LinkedIn](https://linkedin.com/in/dharmaraj-govindhasamy)

---

## ⭐ If This Framework Helped You

Please consider giving it a ⭐ star on GitHub! Your feedback and contributions are appreciated.

---

## 📧 Contact & Support

For issues, questions, or suggestions:
- Open an **Issue** on GitHub
- Email: dharmarajkarthik123@gmail.com
- Create a **Discussion** in the repository

---

**Happy Testing! 🚀**
```
