<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page import="org.jepria.sso.ssoui.main.server.SsoUiServerConstant"%>
<%@ page import="org.jepria.sso.ssoui.main.shared.SsoUiConstant"%>

<%
  Integer attempts = (Integer) session.getAttribute(SsoUiServerConstant.LOGIN_ATTEMPTS_SESSION_ATTRIBUTE);
  if (attempts == null) {
    attempts = 0;
  }
  attempts++;
  session.setAttribute(SsoUiServerConstant.LOGIN_ATTEMPTS_SESSION_ATTRIBUTE, attempts);
  
  RequestDispatcher rd = application.getRequestDispatcher("/WEB-INF/security/SsoUiLogin.jsp");
  request.setAttribute(SsoUiConstant.REQUEST_PARAMETER_IS_ERROR, true);
  rd.forward(request, response);
%>