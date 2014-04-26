package kmap.web.signup.webpages;

import org.openqa.selenium.WebDriver;

/**
 * Created by knoma on 21/04/14.
 */
public class AbstractPage {

    private WebDriver driver;

    public AbstractPage(WebDriver driver) {

        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
