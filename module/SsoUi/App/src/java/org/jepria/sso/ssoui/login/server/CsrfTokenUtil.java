package org.jepria.sso.ssoui.login.server;

import javax.servlet.http.HttpSession;
import java.util.UUID;

public class CsrfTokenUtil {
  public static String generate(HttpSession session) {
    String token = UUID.randomUUID().toString();

    session.setAttribute("csrfTokenServer", token);
    session.setAttribute("csrfTokenAlreadyValidated", Boolean.FALSE);
    return token;
  }

  public static void saveTokenClient(String csrfTokenClient, HttpSession session) {
    session.setAttribute("csrfTokenClient", csrfTokenClient);
  }

  public static boolean validate(HttpSession session) {
    Object csrfTokenClient = session.getAttribute("csrfTokenClient");
    Object csrfTokenServer = session.getAttribute("csrfTokenServer");

    if (csrfTokenServer == null) {
      // the server does not require the token validation
      return true;
    } else {
      return csrfTokenServer.equals(csrfTokenClient);
    }

  }
}
