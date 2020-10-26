package org.jepria.sso.ssoui.login.server;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CsrfTokenValidator implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
          throws IOException, ServletException {
    HttpSession session = ((HttpServletRequest)servletRequest).getSession();
    boolean csrfTokenAlreadyValidated = Boolean.TRUE.equals(session.getAttribute("csrfTokenAlreadyValidated"));

    if (!csrfTokenAlreadyValidated) {
      boolean tokenValid = CsrfTokenUtil.validate(session);
      if (!tokenValid) {
        ((HttpServletRequest) servletRequest).logout();
        ((HttpServletResponse) servletResponse).sendError(401);
        return;
      }
      session.setAttribute("csrfTokenAlreadyValidated", Boolean.TRUE);
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}
