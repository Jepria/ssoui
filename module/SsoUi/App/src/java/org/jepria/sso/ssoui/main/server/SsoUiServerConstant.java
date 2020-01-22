package org.jepria.sso.ssoui.main.server;

import com.technology.jep.jepria.server.JepRiaServerConstant;

public class SsoUiServerConstant extends JepRiaServerConstant {
  
  /**
   * Имя атрибута сессии для хранения информации о количестве совершенных попыток авторизации пользователем
   */
  public static final String LOGIN_ATTEMPTS_SESSION_ATTRIBUTE = "login-attempts";
  
  /**
   * Максимально допустимое количество ошибок ввода некорректных реквизитов (логин/пароль) пользователя
   */
  public static final int MAX_LOGIN_ATTEMPTS = 3;
}
