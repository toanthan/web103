package pqsoft.hrm.config;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pqsoft.hrm.dao.EmployeeRepository;
import pqsoft.hrm.model.Employee;
import pqsoft.hrm.util.SecurityUtils;

@Component
public class HrmAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private EmployeeRepository accountRepos;

  @Autowired
  public HrmAuthenticationSuccessHandler(final EmployeeRepository accountRepos) {
    this.accountRepos = accountRepos;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    HttpSession session = request.getSession();
    final OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
    final Map<String, Object> details =
        (Map<String, Object>) oAuth2Authentication.getUserAuthentication().getDetails();
    final String email = details.get("email").toString();
    final Employee account = accountRepos.findByEmail(email);
    if (Objects.isNull(account)) {
      session.invalidate();
      response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
      response.sendRedirect("/login");
    } else {
      details.put(SecurityUtils.ADMIN, account.getAdmin());
      details.put(SecurityUtils.EMPLOYEE_ID, account.getId());
      session.setAttribute("userDetails", details);
      session.setMaxInactiveInterval(3_600); // 1 hour
      response.setStatus(HttpServletResponse.SC_OK);
      response.sendRedirect("/tasks");
    }
  }
}
