package starter.userpoolmanager;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.JSONObject;

public class GmailLoginSteps {

    private WebDriver driver;
    private String username;
    private String password;

    @When("user navigates to Gmail login page")
    public void navigateToLoginPage() {
        driver = new ChromeDriver();
        driver.get("https://mail.google.com/");
        WebElement usernameField = driver.findElement(By.id("identifierId"));
        WebElement passwordField = driver.findElement(By.name("password"));
    }

    @Then("user logs in using Admin logins")
    public void loginWithAdminCredentials() {
        String secret = secretpull.getSecret();
        JSONObject jsonObject = new JSONObject(secret);
        username = jsonObject.getString("Admin_Username");
        password = jsonObject.getString("Admin_Password");

        // Enter the username and password
        WebElement usernameField = driver.findElement(By.id("identifierId"));
        usernameField.sendKeys(username);
        driver.findElement(By.id("identifierNext")).click();
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);
        driver.findElement(By.id("passwordNext")).click();
    }
}
