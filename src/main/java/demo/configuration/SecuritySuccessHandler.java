package demo.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Test on 9/16/2016.
 */

public class SecuritySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        String targetURL = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }
        try {
            redirectStrategy.sendRedirect(request, response, targetURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Designed only for two roles, and to be changed if a role adding functionality will be implemented
    public String determineTargetUrl(Authentication authentication){
        String url = "/admin/home";
        return url;
    }

    private boolean isUser(String role){
        return role.contains("ADMIN");
    }
}
