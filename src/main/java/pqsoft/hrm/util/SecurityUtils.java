package pqsoft.hrm.util;

import java.util.Map;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class SecurityUtils {

  public static final String ADMIN = "admin";
  public static final String EMPLOYEE_ID = "employee_id";

  public static int getAdmin() {
    final Map<String, Object> details =
        (Map<String, Object>) getAuthentication().getUserAuthentication().getDetails();
    return (int) details.get(ADMIN);
  }

  public static int getEmployeeId() {
    final Map<String, Object> details =
        (Map<String, Object>) getAuthentication().getUserAuthentication().getDetails();
    return (int) details.get(EMPLOYEE_ID);
  }

  private static OAuth2Authentication getAuthentication() {
    return (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
  }
}
