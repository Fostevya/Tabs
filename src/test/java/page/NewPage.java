package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewPage {

    private WebElement openWindowButton;
    private WebDriverWait wait;

    public NewPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//a[contains(.,'Open Window')]"))));
        openWindowButton = driver.findElement(By.xpath("//a[contains(.,'Open Window')]"));
    }

    public void openWindowButtonClick() {
        openWindowButton.click();
    }

    public String getHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }
}
