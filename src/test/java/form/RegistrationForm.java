package form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationForm {

    private WebElement signin;
    private WebDriverWait wait;

    public RegistrationForm(WebDriver driver) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(.,'Signin')]"))));
        signin = driver.findElement(By.xpath("//a[contains(.,'Signin')]"));
    }

    public void clickSignin() {
        signin.click();
    }

}