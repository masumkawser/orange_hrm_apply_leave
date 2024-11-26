from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

# Define constants
URL = "http://your-app-url.com"  # Replace with your application URL
USERNAME = "test_user"          # Replace with valid test username
PASSWORD = "test_password"      # Replace with valid test password
LEAVE_TYPE = "Annual Leave"     # Replace with a valid leave type
LEAVE_START_DATE = "2024-12-01" # Replace with a valid start date
LEAVE_END_DATE = "2024-12-05"   # Replace with a valid end date

# Initialize WebDriver
driver = webdriver.Chrome()  # Make sure ChromeDriver is in PATH or specify its path
wait = WebDriverWait(driver, 10)

try:
    # Step 1: Open the login page
    driver.get(URL)
    driver.maximize_window()

    # Step 2: Perform login
    wait.until(EC.presence_of_element_located((By.ID, "username"))).send_keys(USERNAME)
    driver.find_element(By.ID, "password").send_keys(PASSWORD)
    driver.find_element(By.ID, "loginButton").click()

    # Step 3: Navigate to the "Apply Leave" page
    wait.until(EC.presence_of_element_located((By.ID, "applyLeaveNav"))).click()

    # Step 4: Fill in the leave application form
    wait.until(EC.presence_of_element_located((By.ID, "leaveType"))).send_keys(LEAVE_TYPE)
    driver.find_element(By.ID, "startDate").send_keys(LEAVE_START_DATE)
    driver.find_element(By.ID, "endDate").send_keys(LEAVE_END_DATE)
    driver.find_element(By.ID, "reason").send_keys("Family vacation.")  # Replace with a valid reason

    # Step 5: Submit the leave application
    driver.find_element(By.ID, "submitLeave").click()

    # Step 6: Verify successful leave submission
    confirmation_message = wait.until(EC.presence_of_element_located((By.ID, "confirmationMessage"))).text
    assert "Leave application submitted successfully" in confirmation_message, "Leave application failed!"

    print("Test Passed: Leave application submitted successfully.")

except Exception as e:
    print(f"Test Failed: {e}")

finally:
    # Close the browser
    time.sleep(5)
    driver.quit()
