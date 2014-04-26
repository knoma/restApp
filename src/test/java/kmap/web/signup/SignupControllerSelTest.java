package kmap.web.signup;

import com.gargoylesoftware.htmlunit.html.*;
import kmap.web.config.WebAppConfigurationAware;
import kmap.web.signup.webpages.SignupPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SignupControllerSelTest extends WebAppConfigurationAware {
    private WebDriver driver;


    @Before
    public void setup() {
        driver = new MockMvcHtmlUnitDriver(mockMvc, true);
    }

    @After
    public void destroy() {
        if(driver != null) {
            driver.close();
        }
    }

    @Test
    public void displaysSignupForm() throws Exception {
//        SignupPage signupPage = SignupPage.to(driver);
//        signupPage.signUp(SignupPage.class);

        HtmlPage createMsgFormPage =
                webClient.getPage("http://localhost/signup/signup");

        HtmlForm form = createMsgFormPage.getHtmlElementById("signup");

        HtmlTextInput emailInput    = createMsgFormPage.getHtmlElementById("email");
        emailInput.setValueAttribute("Spring@test.de");
        HtmlPasswordInput passwordInput = createMsgFormPage.getHtmlElementById("password");
        passwordInput.setValueAttribute("Spring");

        HtmlButton submit =
                form.getOneHtmlElementByAttribute("button", "type", "submit");
        HtmlPage newMessagePage = submit.click();

        assertThat(newMessagePage.getUrl().toString()).endsWith("/");
    }
}