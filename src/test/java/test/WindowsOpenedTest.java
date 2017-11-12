package test;

import form.LoginForm;
import form.RegistrationForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.FramesAndWindowsElementPage;
import page.ElementsPage;
import page.NewPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class WindowsOpenedTest {

    private WebDriver driver;
    private String login = "molly";
    private String password = "firstPass";
    private String url = "http://way2automation.com/way2auto_jquery/index.php";
    private RegistrationForm registrationForm;
    private LoginForm loginForm;
    private ElementsPage elementsPage;
    private FramesAndWindowsElementPage framesAndWindowsElementPage;
    private NewPage secondPage;
    private NewPage thirdPage;
    private DesiredCapabilities capabilities;
    private WebDriverWait wait;


    @BeforeTest
    public void beforeTest() {
        capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 10);
            driver.get(url);
            registrationForm = new RegistrationForm(driver);
            registrationForm.clickSignin();
            loginForm = new LoginForm(driver);
            loginForm.authorizeOnSite(login, password);
            elementsPage = new ElementsPage(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void windowsOpenedTest() {
        elementsPage.framesAndWindowsElementClick();
        framesAndWindowsElementPage = new FramesAndWindowsElementPage(driver);
        framesAndWindowsElementPage.openMultipleWindowsTabClick();
        final Set<String> firstWindowsSet = driver.getWindowHandles();
        framesAndWindowsElementPage.openNewWindowButtonClick(driver);
        String newWindow = (wait
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(firstWindowsSet);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                )
        );
        driver.switchTo().window(newWindow);
        final Set<String> secondWindowsSet = driver.getWindowHandles();
        secondPage = new NewPage(driver);
        secondPage.openWindowButtonClick();
        newWindow = (wait
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(secondWindowsSet);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                )
        );
        driver.switchTo().window(newWindow);
        int windowsCount = driver.getWindowHandles().size();
        assertTrue(windowsCount == 5);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
