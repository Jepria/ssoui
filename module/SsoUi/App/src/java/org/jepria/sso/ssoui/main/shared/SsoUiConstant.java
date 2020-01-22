package org.jepria.sso.ssoui.main.shared;
 
import com.technology.jep.jepria.shared.JepRiaConstant;
 
public class SsoUiConstant extends JepRiaConstant  {
  /**
   * Имя параметра http-запроса: означает, что введен неправильный логин/пароль
   */
  public static final String REQUEST_PARAMETER_IS_ERROR = "isError";
  
  /**
   * Имя параметра http-запроса: означает, что пользователь превысил MAX_LOGIN_ATTEMPTS. 
   */
  public static final String REQUEST_PARAMETER_IS_BLOCKED = "isBlocked";
}
