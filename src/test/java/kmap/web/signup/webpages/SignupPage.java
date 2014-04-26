package kmap.web.signup.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by knoma on 21/04/14.
 */
public class SignupPage extends AbstractPage{

    private WebElement summary;

    private WebElement text;

    @FindBy(className = "btn btn-success btn-lg")
    private WebElement submit;

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public <T> T signUp(Class<T> resultPage) {
        this.submit.click();
        return PageFactory.initElements(getDriver(), resultPage);
    }

    public static SignupPage to(WebDriver driver) {
        driver.get("http://localhost:8080/");
        return PageFactory.initElements(driver, SignupPage.class);
    }

}
