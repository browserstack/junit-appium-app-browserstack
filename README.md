# junit-appium-app-browserstack

This repository demonstrates how to run Appium tests in [JUnit4](http://junit.org/junit4/) and [Junit5](https://junit.org/junit5/) on BrowserStack App Automate.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

## Based on

These code samples are currently based on:

- **Appium-Java-Client:** `8.1.1`
- **Protocol:** `W3C`

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

To install the dependencies for JUnit4 tests, run :

- For Android

    ```sh
    cd junit-4/android
    mvn clean
    ```

- For iOS

    ```sh
    cd junit-4/ios
    mvn clean
    ```

Or,

To install the dependencies for JUnit5 tests, run :

- For Android

    ```sh
    cd junit-5/android
    mvn clean
    ```

- For iOS

    ```sh
    cd junit-5/ios
    mvn clean
    ```

## Getting Started

Getting Started with Appium tests in Junit4 and Junit5 on BrowserStack couldn't be easier!

### **Run first test :**

- Junit4
  - Update `browserstack.yml` file at root level of [Android Junit4 examples](junit-4/android) or [iOS Junit4 examples](junit-4/ios) with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)
  - Run `mvn test -P sample-test`

- Junit5
  - Update `browserstack.yml` file at root level of [Android Junit5 examples](junit-5/android) or [iOS Junit5 examples](junit-5/ios) with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)
  - Run `mvn test -P sample-test`

### **Use Local testing for apps that access resources hosted in development or testing environments :**

- Junit4
  - Update `browserstack.yml` file at root level of [Android Junit4 examples](junit-4/android) or [iOS Junit4 examples](junit-4/ios) with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)
  - Simply configure the browserstackLocal parameter in the browserstack.yml file accordingly in [Android Junit4 examples](junit-4/android) or [iOS Junit4 examples](junit-4/ios).
    ```
    browserstackLocal: true
    ```
  - Run `mvn test -P sample-local-test`

  - Junit5
    - Update `browserstack.yml` file at root level of Android Junit5 examples or iOS Junit5 examples with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)
    - Simply configure the browserstackLocal parameter in the browserstack.yml file accordingly in [Android Junit5 examples](junit-5/android) or [iOS Junit5 examples](junit-5/ios).
      ```
      browserstackLocal: true
      ```
    - Run `mvn test -P sample-local-test`

**Note**: If you are facing any issues, refer [Getting Help section](#Getting-Help)

## Integration with other Java frameworks

For other Java frameworks samples, refer to following repositories :

- [TestNG](https://github.com/browserstack/testng-appium-app-browserstack)
- [Java](https://github.com/browserstack/java-appium-app-browserstack)

Note: For other test frameworks supported by App-Automate refer our [Developer documentation](https://www.browserstack.com/docs/)

## Getting Help

If you are running into any issues or have any queries, please check [Browserstack Support page](https://www.browserstack.com/support/app-automate) or [get in touch with us](https://www.browserstack.com/contact?ref=help).
