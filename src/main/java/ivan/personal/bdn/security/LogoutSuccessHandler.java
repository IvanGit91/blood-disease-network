package ivan.personal.bdn.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    public LogoutSuccessHandler() {
    }

    // Just for setting the default target URL
    public LogoutSuccessHandler(String defaultTargetURL) {
        this.setDefaultTargetUrl(defaultTargetURL);
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null && authentication.getDetails() != null) {
            try {
                request.getSession().invalidate();
                for (Cookie cookie : request.getCookies()) {
                    cookie.setMaxAge(-9999);
                }
                authentication.setAuthenticated(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // do whatever you want
        super.onLogoutSuccess(request, response, authentication);
    }
}