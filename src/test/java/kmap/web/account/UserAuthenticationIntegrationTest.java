package kmap.web.account;

import kmap.web.config.WebSecurityConfigurationAware;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class UserAuthenticationIntegrationTest extends WebSecurityConfigurationAware {

    private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

    @Test
    public void requiresAuthentication() throws Exception {
        mockMvc.perform(get("/account/current"))
                .andExpect(redirectedUrl("http://localhost/signin"));
    }

    @Test
    public void userAuthenticates() throws Exception {

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
        final String username = "user";
        ResultMatcher matcher = new ResultMatcher() {
            public void match(MvcResult mvcResult) throws Exception {
                HttpSession session = mvcResult.getRequest().getSession();
                SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
                Assert.assertEquals(securityContext.getAuthentication().getName(), username);
            }
        };

        Map map = new HashMap();
        map.put("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN",
                csrfToken);
        ResultActions perform = mockMvc.perform(post("/j_spring_security_check").param("j_username", username).param("j_password", "demo").param("_csrf", csrfToken.getToken()).sessionAttrs(map));
        perform.andReturn().getRequest().getSession();
        perform
                .andExpect(redirectedUrl("/"))
                .andExpect(matcher);
    }

    @Test
    public void userAuthenticationFails() throws Exception {

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
        Map map = new HashMap();
        map.put("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN",
                csrfToken);

        final String username = "user";
        mockMvc.perform(post("/j_spring_security_check").param("j_username", username).param("j_password", "invalid").param("_csrf", csrfToken.getToken()).sessionAttrs(map))
                .andExpect(redirectedUrl("/signin?error=1"))
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpSession session = mvcResult.getRequest().getSession();
                        SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
                        Assert.assertNull(securityContext);
                    }
                });
    }
}
