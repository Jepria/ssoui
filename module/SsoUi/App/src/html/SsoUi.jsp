<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="org.jepria.sso.ssoui.main.shared.HtmlEscape" %>
<%@ page import="com.technology.jep.jepria.client.JepRiaClientConstant" %>
<%@ page import="com.technology.jep.jepria.server.util.JepServerUtil"%>
<%@ page import="java.util.ResourceBundle" %>
<%@page import="org.jepria.ssoutils.SsoUiConstants"%>
<%@page import="org.jepria.ssoutils.SsoUiUtils"%>
<%@page import="org.jepria.sso.ssoui.login.server.CsrfTokenUtil"%>

<%@ page import="org.jepria.sso.ssoui.login.shared.LoginConstant" %>

<% ResourceBundle jepRiaText = ResourceBundle.getBundle("com.technology.jep.jepria.shared.text.JepRiaText", JepServerUtil.getLocale(request)); %>
<%
  ResourceBundle loginText = ResourceBundle.getBundle(
      "org.jepria.sso.ssoui.login.shared.text.LoginText", JepServerUtil.getLocale(request));
  ResourceBundle ssoUiText = ResourceBundle.getBundle(
      "org.jepria.sso.ssoui.main.shared.text.SsoUiText", JepServerUtil.getLocale(request));
%>

<% 
// имя приложения определяет context path и имена js-файлов
String appName = config.getServletContext().getInitParameter("app.name");
if (appName == null) {
  // получим имя приложения по умолчанию из context path
  appName = request.getContextPath().substring(1);
}
//заголовок приложения
String appTitle = config.getServletContext().getInitParameter("app.title");
if (appTitle == null) {
  // если не указан явно, то это название приложения
  appTitle = config.getServletContext().getInitParameter("app.name");
  if (appTitle == null) {
    // заголовок по умолчанию это имя приложения из context path
    appTitle = request.getContextPath().substring(1);
  }
}
%>

<html style="width: 100%; height: 100%;" id="<%= SsoUiConstants.LOGIN_FORM_HTML_ID %>">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

<%
  // приложение SsoUi имеет свой заголовок (который может отличаться от стандартных appTitle и appName)
  String ssoUiTitle = request.getParameter(SsoUiConstants.REQUEST_PARAMETER_APP_TITLE);
  if (ssoUiTitle == null) {
    ssoUiTitle = ssoUiText.getString("app.title");
  }
  
  // Защита от инъекции js-кода
  ssoUiTitle = HtmlEscape.escape(ssoUiTitle);
%>

<%
  // факт попадания в приложение SsoUi говорит о статусе 401 Unauthorized
  response.setStatus(401);
%>
    <!--                                           -->
    <!-- Any title is fine                         -->
    <!--                                           -->
    <title><%= ssoUiTitle %></title>
    
    <!--                                           -->
    <!-- This script loads your compiled module.   -->
    <!-- If you add any GWT meta tags, they must   -->
    <!-- be added before this line.                 -->
    <!--                                           -->
    <script type="text/javascript" language="javascript" src="<%= appName %>/<%= appName %>.nocache.js"></script>
    <style>
      td {
        text-align: left;
      }
    </style>
  </head>

  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
    
    <!-- OPTIONAL: include this if you want history support -->
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position: absolute; width: 0; height: 0; border: 0;"></iframe>
    
    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div class="jepRia-noJavaScriptEnabledMessage"><%= jepRiaText.getString("noJavaScriptEnabledMessage") %></div>
    </noscript>
    
    <div id="testBuildMessage" class="<%= JepRiaClientConstant.TEST_BUILD_MESSAGE_CLASS %>"> 
      <div class="jepRia-testBuildMessageNotification error"> 
        <div class="jepRia-testBuildMessageClose" onclick="document.getElementById('testBuildMessage').style.display = 'none';">
          ×
        </div> 
        <div class="jepRia-testBuildMessageHeader">
          Attention please!
        </div> 
        <div class="jepRia-testBuildMessageInfo">
          This is test build!
        </div> 
      </div>
    </div>

    <div id="loadingProgress" class="jepRia-fullScreenContainer">
      <div class="jepRia-fullScreenContainerCenter jepRia-loadingProgress">
          <img class="jepRia-loadingProgressImg" src="images/loading.gif" width="32" height="32" alt="Loading..."/>
            <div>
              <p>
                <span id="loadingHeader"><%= appTitle %></span>
              </p>
            <span id="loadingMessage" class="jepRia-loadingMessage"><%= jepRiaText.getString("loadingMessage") %></span>
          </div>
      </div>
    </div>

    <table style="width: 100%">
      <tr>
        <th id="login.title" class="ssoui-loginModuleTitle"><%= ssoUiTitle %></th>
      </tr>
    </table>
    
    <form id="<%= LoginConstant.LOGIN_FORM_ID %>" class="ssoui-loginForm" target="_self" method="post" 
      action="<%= SsoUiUtils.getSsoUiContext(pageContext.getServletContext()) + "/j_security_check" %>">
      
      <table style=" width: 100%; table-layout: fixed; ">
        <colgroup>
          <col style=" width: 50%; ">
          <col style=" width: 480px; ">
          <col style=" width: 50%; ">
        </colgroup>
        <tr>
          <td colspan="3">&nbsp;</td>
        </tr>
  
        <tr>
          <td style=" text-align: center; vertical-align: middle;" colspan="3">
            <div id="<%= LoginConstant.WRONG_CREDENTIAL_ERROR_MESSAGE %>" class="ssoui-errorMessage"
              style="display: none; aria-hidden: true;">
              <%= loginText.getString("login.authorizationError.wrongCredential") %>
            </div>
            <div id="<%= LoginConstant.ATTEMPTS_ERROR_MESSAGE %>" class="ssoui-errorMessage"
              style="display: none; aria-hidden: true;">
              <%= jepRiaText.getString("loginError.attemptsError") %>
            </div>
          </td>
        </tr>
  
        <tr>
          <td/>
          <td style="padding-left: 60px"> <!-- Empirical value that equals the ACTUAL width of the fieldLabel  -->
            <div id="<%= LoginConstant.LOGIN_FIELD_SLOT_ID %>" style="width:100%"></div>
          </td>
          <td/>
        </tr>
  
        <tr>
          <td/>
          <td style="padding-left: 60px"> <!-- Empirical value that equals the ACTUAL width of the fieldLabel  -->
            <div id="<%= LoginConstant.PASSWORD_FIELD_SLOT_ID %>" style="width:100%"></div>
          </td>
          <td/>
        </tr>
  
        <tr>
          <td style=" text-align: center; vertical-align: middle;" colspan="3">
            <div id="<%= LoginConstant.SUBMIT_BUTTON_SLOT_ID %>" style="width:100%"></div>
          </td>
        </tr>
  
      </table>

      <input id="x_csrf_token_field" type="hidden" name="x-csrf-token" value="<%= CsrfTokenUtil.generate(session) %>">
    </form>
    
    <!-- According to HTML5 Specification we can place link and style tags in any place inside <BODY> -->
    <!-- For that purpose we should use attribute 'property' -->
    <!-- It allows us to guarantee that all our styles will be applied in correct order without replacing GWT styles-->
    <link type="text/css" rel="stylesheet" property='stylesheet' href="css/JepRia.css" />
    <link type="text/css" rel="stylesheet" property='stylesheet' href="css/<%= appName %>.css" />
  </body>
</html>
