package form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginForm {

    private WebElement loginField;
    private WebElement passwordField;
    private WebElement submitButton;

    private WebDriverWait wait;

    public LoginForm(WebDriver driver) {
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver
                .findElement(By.xpath("//h3[contains(.,'Login')]/..//input[@name='username']"))));
        loginField = driver.findElement(By.xpath("//h3[contains(.,'Login')]/..//input[@name='username']"));
        passwordField = driver.findElement(By.xpath("//h3[contains(.,'Login')]/..//input[@name='password']"));
        submitButton = driver.findElement(By.xpath("//h3[contains(.,'Login')]/..//input[contains(@type,'submit')]"));
    }

    private void enterLogin(String loginString) {
        loginField.sendKeys(loginString);
    }

    private void enterPassword(String passwordString) {
        passwordField.sendKeys(passwordString);
    }

    private void submit() {
        submitButton.click();
    }

    public void authorizeOnSite(String loginString, String passwordString) {
        enterLogin(loginString);
        enterPassword(passwordString);
        submit();
        wait.until(ExpectedConditions.invisibilityOf(submitButton));
    }

}