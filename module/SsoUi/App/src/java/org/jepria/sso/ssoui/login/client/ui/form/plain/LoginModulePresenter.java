package org.jepria.sso.ssoui.login.client.ui.form.plain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jepria.sso.ssoui.login.client.LoginClientFactoryImpl;
import org.jepria.sso.ssoui.login.shared.service.LoginServiceAsync;
import org.jepria.sso.ssoui.main.shared.SsoUiConstant;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.technology.jep.jepria.client.async.JepAsyncCallback;
import com.technology.jep.jepria.client.history.scope.JepScopeStack;
import com.technology.jep.jepria.client.ui.eventbus.event.EnterModuleEvent;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.plain.PlainModulePresenter;

public class LoginModulePresenter 
    extends PlainModulePresenter<LoginModuleView, PlainEventBus, LoginServiceAsync, LoginClientFactoryImpl> {
  
  public LoginModulePresenter(String moduleId, Place place, LoginClientFactoryImpl clientFactory) {
    super(moduleId, place, clientFactory);
   }   
  
  /**
   * Флаг, означающий, что запрос уже выполнен.
   * Необходим для предотвращения повторного запроса при
   * случайном или намеренном повторном нажатии на Enter.
   */
  private boolean alreadySubmitted = false;
  
  @Override
  public void bind() {
    super.bind();
    view.addLogonButtonHandler(new ClickHandler(){
      @Override
      public void onClick(ClickEvent event) {
        doLogin();
      }});
    
    view.setEnterKeyCommand(new Command(){
      @Override
      public void execute() {
        doLogin();
      }});
  }

  /**
   * Метод переопределён в связи с отсутствием в данном модуле main view.
   */
  @Override
  protected void setMainView() {
    mainEventBus.setMainView(null);
  }

  /**
   * Выполняет действия, требуемые для аутентификации.<br/>
   * Проверяется, не был ли запрос уже выполнен и валидны ли введённые в поля
   * значения. Если проверки пройдены, вызывает {@link LoginModuleView#submit()}
   * для отправки запроса и {@link LoginModuleView#removeLoginWidgets()} для удаления виджетов.
   */
  private void doLogin() {
    if (!alreadySubmitted && view.isValid()) {
      view.showLoadingIndicator();
      
      // разлогиниваем возможно уже залогиненного пользователя. Это нужно в следующем случае:
      // Если залогиненный пользователь повторно зашёл на форму авторизации по прямой ссылке на SsoUi (а не по редиректу из приложения),
      // расцениваем это как сознательный перелогин.
      JepScopeStack.instance.getMainClientFactory().getMainService().logout(null, new JepAsyncCallback<String>() {
        @Override
        public void onSuccess(String result) {
          
          // перед сабмитом пронесём все get-параметры с клиента на сервер (к этому времени сессия могла истечь, а параметры -- не сохраниться)

          String csrfToken = view.getCsrfToken();

          service.bindParamsToSession(
                  csrfToken,
              convertParams(Window.Location.getParameterMap()),
              new JepAsyncCallback<Void>() {
                @Override
                public void onSuccess(Void arg0) {
                  
                  view.submit();
                  alreadySubmitted = true;
                  view.removeLoginWidgets();

                }
              });
        }
      });
    }
  }
  
  /**
   * Преобразует параметры из Map&lt;String, List&lt;String&gt;&gt; в Map&lt;String, String&gt;,
   * где значения последнего &mdash; это <b>первый</b> элемент списка значений первого
   * 
   * @param params
   * @return
   */
  private static Map<String, String> convertParams(Map<String, List<String>> params) {
    if (params == null) {
      return null;
    } else {
      return params.entrySet().stream().collect(Collectors.<Map.Entry<String, List<String>>, String, String>toMap(
          e -> e.getKey(),
          e -> {
            return e.getValue() == null || e.getValue().isEmpty() ? null : e.getValue().get(0);
          })); 
    }
  }

  /**
   * Переопределённый метод дополнительно отображает актуальные сообщения об ошибках
   * и устанавливает фокус на поле логина.
   * @param event событие входа в модуль
   */
  @Override
  public void onEnterModule(EnterModuleEvent event) {
    super.onEnterModule(event);    
    view.setLoginFieldFocused();    

    boolean isWrongCredential = 
        "1".equals(Window.Location.getParameter(SsoUiConstant.REQUEST_PARAMETER_IS_ERROR));
    view.setWrongCredentialErrorMessageVisible(isWrongCredential);
    boolean isBlocked = 
        "1".equals(Window.Location.getParameter(SsoUiConstant.REQUEST_PARAMETER_IS_BLOCKED));
    view.setAttemptsErrorMessageVisible(isBlocked);
    view.setLoginWidgetsVisible(!isBlocked);
  }
}
