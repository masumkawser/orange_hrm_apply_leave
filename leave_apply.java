import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ApplyLeaveAutomation {

    public static void main(String[] args) {
        // Set up WebDriver path (if not in system PATH)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            // Step 1: Open the login page
            driver.get("http://your-app-url.com"); // Replace with your application URL
            driver.manage().window().maximize();

            // Step 2: Perform login
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            usernameField.sendKeys("test_user"); // Replace with valid username
            passwordField.sendKeys("test_password"); // Replace with valid password
            loginButton.click();

            // Step 3: Navigate to "Apply Leave" page
            WebElement applyLeaveNav = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("applyLeaveNav")));
            applyLeaveNav.click();

            // Step 4: Fill in the leave application form
            WebElement leaveTypeDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("leaveType")));
            WebElement startDateField = driver.findElement(By.id("startDate"));
            WebElement endDateField = driver.findElement(By.id("endDate"));
            WebElement reasonField = driver.findElement(By.id("reason"));
            WebElement submitButton = driver.findElement(By.id("submitLeave"));

            leaveTypeDropdown.sendKeys("Annual Leave"); // Replace with valid leave type
            startDateField.sendKeys("2024-12-01"); // Replace with valid start date
            endDateField.sendKeys("2024-12-05"); // Replace with valid end date
            reasonField.sendKeys("Family vacation."); // Replace with a valid reason
            submitButton.click();

            // Step 5: Verify successful leave submission
            WebElement confirmationMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmationMessage")));
            String message = confirmationMessage.getText();
            if (message.contains("Leave application submitted successfully")) {
                System.out.println("Test Passed: Leave application submitted successfully.");
            } else {
                System.out.println("Test Failed: Unexpected confirmation message.");
            }

        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
