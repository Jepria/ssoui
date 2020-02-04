<!DOCTYPE html>
<%@ page import="org.jepria.ssoutils.SsoUiConstants"%>
<%@ page import="org.jepria.ssoutils.SsoUiUtils"%>

<%
  // Параметры должны быть в сессии (в сессию они помещаются в LoginServiceImpl).
  String enterModule = (String)session.getAttribute(SsoUiConstants.REQUEST_PARAMETER_ENTER_MODULE);
  String queryStringEncoded = (String)session.getAttribute(SsoUiConstants.REQUEST_PARAMETER_QUERY_STRING);
  String fragmentEncoded = (String)session.getAttribute(SsoUiConstants.REQUEST_PARAMETER_FRAGMENT);
  
  // формируем URL с параметрами
  StringBuilder fwdUrlSb = new StringBuilder();

  // the enterModule must start with a single slash. More slashes may lead to the "open redirect" vulnerability
  while (enterModule.charAt(0) == '/') {
    enterModule = enterModule.substring(1);
  }
  if (enterModule.charAt(0) != '/') {
    enterModule = "/" + enterModule;
  }

  fwdUrlSb.append(enterModule);
  
  if (queryStringEncoded != null) {
    String queryStringDecoded = SsoUiUtils.decodeQueryStr(queryStringEncoded);
    fwdUrlSb.append('?').append(queryStringDecoded);
  }
  
  if (fragmentEncoded != null) {
    String fragmentDecoded = SsoUiUtils.decodeFragment(fragmentEncoded);
    fwdUrlSb.append('#').append(fragmentDecoded);
  }

  String redirectUrl = fwdUrlSb.toString();
  
  // Перед редиректом закодируем только пробелы (могли иметь место в fragment)
  // Не эквивалентно URLEncoder.encode, так как прочие символы кодировать не нужно
  redirectUrl = redirectUrl.replaceAll(" ", "%20");

  response.sendRedirect(redirectUrl);
%>