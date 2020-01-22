package org.jepria.sso.ssoui.login.shared.service;

import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.technology.jep.jepria.shared.service.data.JepDataService;

@RemoteServiceRelativePath("LoginService")
public interface LoginService extends JepDataService {
  /**
   * Привязывает get-параметры на сервер.
   * Необходимо для вызова перед сабмитом логин-формы.
   * Иначе, если пользователь долго не будет вводить логин-пароль на форму ввода, то
   * сессия истечет, и потребуется перезагрузка логин-страницы
   * (параметры при этом не сохранятся).
   */
  void bindParamsToSession(Map<String, String> params);
}
