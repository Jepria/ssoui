package org.jepria.sso.ssoui.login.shared.text;

/**
 * Interface to represent the constants contained in resource bundle:
 * 	'C:/OS_Jep_Svn/Module/SsoUi/Trunk/App/src/java/org/jepria/sso/ssoui/login/shared/text/LoginText.properties'.
 */
public interface LoginText extends com.google.gwt.i18n.client.Constants {
  
  /**
   * Translated "Данные неверны".
   * 
   * @return translated "Данные неверны"
   */
  @DefaultStringValue("Данные неверны")
  @Key("login.authorizationError.wrongCredential")
  String login_authorizationError_wrongCredential();

  /**
   * Translated "Логин".
   * 
   * @return translated "Логин"
   */
  @DefaultStringValue("Логин")
  @Key("login.detail.login")
  String login_detail_login();

  /**
   * Translated "Войти".
   * 
   * @return translated "Войти"
   */
  @DefaultStringValue("Войти")
  @Key("login.detail.logonButton.title")
  String login_detail_logonButton_title();

  /**
   * Translated "Пароль".
   * 
   * @return translated "Пароль"
   */
  @DefaultStringValue("Пароль")
  @Key("login.detail.password")
  String login_detail_password();

  /**
   * Translated "Проверка данных...".
   * 
   * @return translated "Проверка данных..."
   */
  @DefaultStringValue("Проверка данных...")
  @Key("login.loadingText.authorizationProcess")
  String login_loadingText_authorizationProcess();

  /**
   * Translated "Авторизация".
   * 
   * @return translated "Авторизация"
   */
  @DefaultStringValue("Авторизация")
  @Key("login.title")
  String login_title();
}
