# junit-appium-app-browserstack

This repository demonstrates how to run Appium tests in [JUnit4](http://junit.org/junit4/) and [Junit5](https://junit.org/junit5/) on BrowserStack App Automate.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

## Setup

### Requirements

1. Java 8+

    - If Java is not installed, follow these instructions:
        - For Windows, download latest java version from [here](https://java.com/en/download/) and run the installer executable
        - For Mac and Linux, run `java -version` to see what java version is pre-installed. If you want a different version download from [here](https://java.com/en/download/)

2. Maven
   - If Maven is not downloaded, download it from [here](https://maven.apache.org/download.cgi)
   - For installation, follow the instructions [here](https://maven.apache.org/install.html)

### Install the dependencies

To install the dependencies for Android tests, run :

- For Junit4

    ```sh
    cd android/junit4-examples
    mvn clean
    ```

- For Junit5

    ```sh
    cd android/junit5-examples
    mvn clean
    ```

Or,

To install the dependencies for iOS tests, run :

- For Junit4

    ```sh
    cd ios/junit4-examples
    mvn clean
    ```

- For Junit5

    ```sh
    cd ios/junit5-examples
    mvn clean
    ```

## Getting Started

Getting Started with Appium tests in Junit4 and Junit5 on BrowserStack couldn't be easier!

### **Run first test :**

- Junit4
  - Switch to `run_first_test` directory under [Android Junit4 examples](android/junit4-examples) or [iOS Junit4 examples](ios/junit4-examples)
  - Follow the steps outlined in the documentation - [Get Started with your first test on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java/junit)

- Junit5
  - Switch to `run_first_test` directory under [Android Junit5 examples](android/junit5-examples) or [iOS Junit5 examples](ios/junit5-examples)
  - Follow the steps outlined in the documentation - [Get Started with your first test on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java/junit)

### **Speed up test execution with parallel testing :**

- Junit4
  - Switch to `run_parallel_test` directory under [Android Junit4 examples](android/junit4-examples/) or [iOS Junit4 examples](ios/junit4-examples/)
  - Follow the steps outlined in the documentation - [Get Started with parallel testing on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java/junit/parallelize-tests)
  
- Junit5
  - Switch to `run_parallel_test` directory under [Android Junit5 examples](android/junit5-examples/) or [iOS Junit5 examples](ios/junit5-examples/)
  - Follow the steps outlined in the documentation - [Get Started with parallel testing on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java/junit/parallelize-tests)

### **Use Local testing for apps that access resources hosted in development or testing environments :**

- Junit4
  - Switch to `run_local_test` directory under [Android Junit4 examples](android/junit4-examples/) or [iOS Junit4 examples](ios/junit4-examples/)
  - Follow the steps outlined in the documentation - [Get Started with Local testing on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java/junit/local-testing)

- Junit5
  - Switch to `run_local_test` directory under [Android Junit5 examples](android/junit5-examples/) or [iOS Junit5 examples](ios/junit5-examples/)
  - Follow the steps outlined in the documentation - [Get Started with Local testing on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java/junit/local-testing)

**Note**: If you are facing any issues, refer [Getting Help section](#Getting-Help)

## Integration with other Java frameworks

For other Java frameworks samples, refer to following repositories :

- [TestNG](https://github.com/browserstack/testng-appium-app-browserstack)
- [Java](https://github.com/browserstack/java-appium-app-browserstack)

Note: For other test frameworks supported by App-Automate refer our [Developer documentation](https://www.browserstack.com/docs/)

## Getting Help

If you are running into any issues or have any queries, please check [Browserstack Support page](https://www.browserstack.com/support/app-automate) or [get in touch with us](https://www.browserstack.com/contact?ref=help).
