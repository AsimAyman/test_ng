import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginEle {
    WebDriver driver ;
    LoginEle(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id ="username")
    WebElement userNameEle;
    WebElement getUserNameEle(){
        return  driver.findElement(By.id("username"));
    }
    WebElement getPasswordEle() {
        return  driver.findElement(By.cssSelector("input[type=\"password\"]"));
    }
    WebElement getLoginBtn(){
        return driver.findElement(By.className("fa"));
    }
    void login(String name, String password) {
        userNameEle.sendKeys(name);
        getPasswordEle().sendKeys(password);
        getLoginBtn().click();
    }
}
