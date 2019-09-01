package pqsoft.hrm.util;

import java.util.Map;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class SecurityUtils {

  public static int getAdmin() {
    final Map<String, Object> details =
        (Map<String, Object>)
            ((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication())
                .getUserAuthentication()
                .getDetails();
    return (int) details.get("admin");
  }

  public static int getEmployeeId() {
    final Map<String, Object> details =
        (Map<String, Object>)
            ((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication())
                .getUserAuthentication()
                .getDetails();
    return (int) details.get("employee_id");
  }
}
