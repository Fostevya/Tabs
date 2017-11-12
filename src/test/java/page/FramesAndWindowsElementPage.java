package page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FramesAndWindowsElementPage {

    private WebElement openMultipleWindowsTab;
    private WebElement openMultiplePagesButton;
    private WebDriverWait wait;

    public FramesAndWindowsElementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//a[contains(.,'Open Multiple Windows')]"))));
        openMultipleWindowsTab = driver.findElement(By.xpath("//a[contains(.,'Open Multiple Windows')]"));
        driver.switchTo().frame(3);
        openMultiplePagesButton = driver
                .findElement(By.xpath("//a[contains(concat(' ',@onclick,' '),'window.open')]"));
        driver.switchTo().defaultContent();
    }

    public void openMultipleWindowsTabClick() {
        openMultipleWindowsTab.click();
    }

    public void openNewWindowButtonClick(WebDriver driver) {
        driver.switchTo().frame(3);
        openMultiplePagesButton.click();
    }
}