package org.jepria.sso.ssoui.login.client.ui.form.plain;
 
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.technology.jep.jepria.client.ui.plain.PlainModuleView;

public interface LoginModuleView extends PlainModuleView {
  /**
   * Добавляет обработчик нажатия на кнопку logon.
   * @param handler обработчик
   */
  void addLogonButtonHandler(ClickHandler handler);
  /**
   * Проверяет, являются ли введённые значения валидными.
   * @return true, если значения валидны, false в противном случае
   */
  boolean isValid();
  /**
   * Удаляет виджеты аутентификации с представления.
   */
  void removeLoginWidgets();
  /**
   * Регулирует отображение сообщения о превышении допустимого числа попыток логина.
   * @param visible true - показать, false - скрыть
   */
  void setAttemptsErrorMessageVisible(boolean visible);
  /**
   * Устанавливает обработчик нажатия на кнопку Enter.<br/>
   * Обработка реализована с помощью паттерна Command, т.к. в GWT отсутствует
   * специализированный тип обработчика (handler) нажатия на Enter.
   * @param command обработчик
   */
  void setEnterKeyCommand(Command command);
  /**
   * Устанавливает фокус на поле логина.
   */
  void setLoginFieldFocused();
  /**
   * Регулирует отображение виджетов, отвечающих за аутентификацию.
   * @param visible true - показать, false - скрыть
   */
  void setLoginWidgetsVisible(boolean visible);
  /**
   * Регулирует отображение сообщения о неверном логине/пароле.
   * @param visible true - показать, false - скрыть
   */
  void setWrongCredentialErrorMessageVisible(boolean visible);
  /**
   * Отображает индикатор загрузки.
   */
  void showLoadingIndicator();
  /**
   * Выполняет запрос на аутентификацию.
   */
  void submit();

  String getCsrfToken();
}
