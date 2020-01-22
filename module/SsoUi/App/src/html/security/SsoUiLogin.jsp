<!DOCTYPE html>

<%@ page import="org.jepria.sso.ssoui.main.shared.SsoUiConstant"%>
<%@ page import="org.jepria.sso.ssoui.main.server.SsoUiServerConstant"%>
<%@ page import="org.jepria.ssoutils.SsoUiConstants"%>
<%@ page import="org.jepria.ssoutils.SsoUiUtils"%>
<%@ page import="java.lang.StringBuilder"%>

<% 
  boolean isError = Boolean.TRUE.equals(request.getAttribute(SsoUiConstant.REQUEST_PARAMETER_IS_ERROR));
  Integer loginAttempts = (Integer) session.getAttribute(SsoUiServerConstant.LOGIN_ATTEMPTS_SESSION_ATTRIBUTE);
  
  String enterModule;
  String queryString;
  String fragment;
  String appTitle;
  String locale;
  
  // Параметры должны быть в запросе...
  enterModule = request.getParameter(SsoUiConstants.REQUEST_PARAMETER_ENTER_MODULE);
  if (enterModule != null) {
    queryString = request.getParameter(SsoUiConstants.REQUEST_PARAMETER_QUERY_STRING);
    fragment = request.getParameter(SsoUiConstants.REQUEST_PARAMETER_FRAGMENT);
    appTitle = request.getParameter(SsoUiConstants.REQUEST_PARAMETER_APP_TITLE);
    locale = request.getParameter(SsoUiConstants.REQUEST_PARAMETER_LOCALE);
  } else {
    // ...если не в запросе, тогда в сессии (например, в случае, если попали сюда со страницы LoginError).
    // (в сессию параметры помещаются в LoginServiceImpl)
    enterModule = (String)session.getAttribute(SsoUiConstants.REQUEST_PARAMETER_ENTER_MODULE);
    queryString = (String)session.getAttribute(SsoUiConstants.REQUEST_PARAMETER_QUERY_STRING);
    fragment = (String)session.getAttribute(SsoUiConstants.REQUEST_PARAMETER_FRAGMENT);
    appTitle = (String)session.getAttribute(SsoUiConstants.REQUEST_PARAMETER_APP_TITLE);
    locale = (String)session.getAttribute(SsoUiConstants.REQUEST_PARAMETER_LOCALE);
  }
  
  // формируем URL с параметрами
  StringBuilder loginUrlSb = new StringBuilder();
  
  loginUrlSb.append(request.getContextPath()).append("/SsoUi.jsp?em=Login");
  
  // пронос пришедших GET-параметров далее на клиент
    
  loginUrlSb.append('&').append(SsoUiConstants.REQUEST_PARAMETER_ENTER_MODULE).append('=').append(enterModule);
  
  if (queryString != null) {
    loginUrlSb.append('&').append(SsoUiConstants.REQUEST_PARAMETER_QUERY_STRING).append('=').append(queryString);
  }
  if (fragment != null) {
    loginUrlSb.append('&').append(SsoUiConstants.REQUEST_PARAMETER_FRAGMENT).append('=').append(fragment);
  }
  
  if (appTitle != null) {
    loginUrlSb.append('&').append(SsoUiConstants.REQUEST_PARAMETER_APP_TITLE).append('=').append(appTitle);
  }
  
  if (loginAttempts != null && loginAttempts >= SsoUiServerConstant.MAX_LOGIN_ATTEMPTS) {
    loginUrlSb.append('&').append(SsoUiConstant.REQUEST_PARAMETER_IS_BLOCKED).append("=1");
  } else if (isError) {
    loginUrlSb.append('&').append(SsoUiConstant.REQUEST_PARAMETER_IS_ERROR).append("=1");
  }
  
  if (locale != null) {
    loginUrlSb.append('&').append(SsoUiConstants.REQUEST_PARAMETER_LOCALE).append('=').append(locale);
  }
  
  String redirectUrl = loginUrlSb.toString();
  
  // Перед редиректом закодируем только пробелы (могли иметь место в fragment)
  // Не эквивалентно URLEncoder.encode, так как прочие символы кодировать не нужно
  redirectUrl = redirectUrl.replaceAll(" ", "%20");
  
  response.sendRedirect(redirectUrl);
%>