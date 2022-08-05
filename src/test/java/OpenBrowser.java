import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class OpenBrowser {
    WebDriver driver;
    LoginEle loginEle;

    @BeforeClass
    void openBrowser() throws InterruptedException {
        System.out.println("Before");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        loginEle = new LoginEle(driver);
        driver.manage().window().maximize();
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        Thread.sleep(3000);
    }

    @Test
    void validLogin(){
        loginEle.login("tomsmith", "SuperSecretPassword!");
        String expectedResult = "You logged into a secure area!";
        String actualResult = driver.findElement(By.id("flash")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualResult.contains(expectedResult), true, "frist assertion");
        softAssert.assertTrue(driver.findElement(By.cssSelector("i[class=\"icon-2x icon-signout\"]")).isDisplayed(), "secound assertion");
        softAssert.assertEquals(driver.getCurrentUrl().contains("https://the-internet.herokuapp.com/secure"), true, "Third assertion");
        softAssert.assertAll();

        //hard assertion
//           System.out.println("frist assertion");
//           Assert.assertEquals(actualResult.contains(expectedResult),true);
//           System.out.println("second assertion");
//           Assert.assertTrue(driver.findElement(By.cssSelector("i[class=\"icon-2x icon-signout\"]")).isDisplayed());
//           System.out.println("third asseretion");
//           Assert.assertEquals(driver.getCurrentUrl().contains("https://the-internet.herokuapp.com/secure"),true);
        //soft assertion
    }

    @Test
    void invalidLogin() {
        loginEle.login("tomsmith1", "SuperSecretPassword!1 ");
        System.out.println("Test 2");
        String expectedResult = "Your username is invalid!";
        String actualResult = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @AfterClass
    void closeBrowser() throws InterruptedException {
        System.out.println("After");
        Thread.sleep(3000);
        driver.quit();
    }


}

