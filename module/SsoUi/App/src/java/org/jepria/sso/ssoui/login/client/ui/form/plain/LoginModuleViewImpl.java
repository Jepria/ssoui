package org.jepria.sso.ssoui.login.client.ui.form.plain;
 
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.LOGIN_BUTTON_ID;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.LOGIN_PASSWORD_FIELD_ID;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.LOGIN_USERNAME_FIELD_ID;
import static org.jepria.sso.ssoui.login.client.LoginClientConstant.loginText;

import org.jepria.sso.ssoui.login.shared.LoginConstant;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.technology.jep.jepria.client.ui.plain.PlainModuleViewImpl;
import com.technology.jep.jepria.client.util.JepClientUtil;
import com.technology.jep.jepria.client.widget.field.multistate.JepTextField;

public class LoginModuleViewImpl extends PlainModuleViewImpl implements LoginModuleView { 
  
  /**
   * Ширина компонент редактирования полей логина и пароля.
   */
  private static final int INPUT_FIELD_WIDTH = 220;
  /**
   * Ширина меток полей логина и пароля.
   */
  private static final int LABEL_WIDTH = 70;
  /**
   * Кнопка аутентификации.
   */
  private final Button logonButton = createLogonButton();
  /**
   * Панель, соответствующая HTML-форме аутентификации.
   */
  private final RootPanel logonFormPanel = RootPanel.get(LoginConstant.LOGIN_FORM_ID);
  /**
   * Поле ввода логина.
   */
  private final JepTextField loginTextField = createLoginField();
  /**
   * Поле ввода пароля.
   */
  private final JepTextField passwordTextField = createPasswordField();
  /**
   * Обработчик события нажатия на Enter.
   */
  private Command enterKeyCommand;
  
  public LoginModuleViewImpl() {
    addWidget(LoginConstant.LOGIN_FIELD_SLOT_ID, loginTextField);    
    addWidget(LoginConstant.PASSWORD_FIELD_SLOT_ID, passwordTextField);
    addWidget(LoginConstant.SUBMIT_BUTTON_SLOT_ID, logonButton);
    addEnterKeyListener();
  }

  private Button createLogonButton() {
    Button logonButton = new Button(loginText.login_detail_logonButton_title());
    logonButton.getElement().setId(LOGIN_BUTTON_ID);
    logonButton.setTitle(loginText.login_detail_logonButton_title());
    logonButton.setWidth(115 + Unit.PX.getType());
    return logonButton;
  }

  /**
   * Создаёт поле ввода логина.<br/>
   * Особенность: компоненте редактирования задаётся атрибут
   * name в рамках HTML-формы.
   * @return поле ввода логина
   */
  private JepTextField createLoginField() {
    JepTextField loginTextField = new JepTextField(loginText.login_detail_login());
    loginTextField.setLabelWidth(LABEL_WIDTH);
    loginTextField.setAllowBlank(false);
     
    TextBox loginTextBox = loginTextField.getEditableCard();
    loginTextBox.setWidth(INPUT_FIELD_WIDTH + Unit.PX.getType());
    loginTextBox.setName(LOGIN_USERNAME_FIELD_ID);
    loginTextBox.getElement().setId(LOGIN_USERNAME_FIELD_ID);
    
    return loginTextField;
  }

  /**
   * Создаёт поле ввода пароля.<br/>
   * Особенности: 1) в качестве компонента редактирования используется
   * {@link PasswordTextBox}; 2) компоненте редактирования задаётся
   * атрибут name в рамках HTML-формы.
   * @return поле ввода пароля
   */
  private JepTextField createPasswordField() {
    JepTextField passwordTextField = new JepTextField(loginText.login_detail_password()) {
      @Override
      protected void addEditableCard() {
        editableCard = new PasswordTextBox();
        editablePanel.add(editableCard);
      }
    };
    passwordTextField.setLabelWidth(LABEL_WIDTH);
    passwordTextField.setAllowBlank(false);
    PasswordTextBox passwordTextBox = (PasswordTextBox) passwordTextField.getEditableCard();
    passwordTextBox.setName(LOGIN_PASSWORD_FIELD_ID);
    passwordTextBox.getElement().setId(LOGIN_PASSWORD_FIELD_ID);
    passwordTextBox.setWidth(INPUT_FIELD_WIDTH + Unit.PX.getType());
    return passwordTextField;
  }
  
  /**
   * Добавляет виджет в слот с заданным идентификатором.
   * @param panelId идентификатор слота
   * @param widget виджет
   */
  private void addWidget(String panelId, IsWidget widget) {
    RootPanel.get(panelId).add(widget);
  }

  /**
   * Устанавливает GWT-обработчик нажатия на клавишу Enter.<br/>
   * Если поле {@link #enterKeyCommand} содержит объект, реализующий интерфейс
   * {@link Command}, то у него вызывается метод {@link Command#execute()}.
   */
  private void addEnterKeyListener() {
    RootPanel.get().addDomHandler(new KeyDownHandler() {
      public void onKeyDown(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER && enterKeyCommand != null) {
          enterKeyCommand.execute();
        }
      }
    }, KeyDownEvent.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addLogonButtonHandler(ClickHandler handler) {
    logonButton.addClickHandler(handler);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isValid() {
    return loginTextField.isValid() & passwordTextField.isValid();
  }

  /**
   * Удаление содержимого формы ввода логина и пароля реализовано
   * путём удаления всех дочерних элементов HTML-формы.
   */
  @Override
  public void removeLoginWidgets() {
    logonFormPanel.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setAttemptsErrorMessageVisible(boolean visible) {
    RootPanel.get(LoginConstant.ATTEMPTS_ERROR_MESSAGE).setVisible(visible);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnterKeyCommand(Command command) {
    this.enterKeyCommand = command;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLoginFieldFocused() {
    loginTextField.getEditableCard().setFocus(true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLoginWidgetsVisible(boolean visible) {
    logonButton.setVisible(visible);
    loginTextField.setVisible(visible);
    passwordTextField.setVisible(visible);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setWrongCredentialErrorMessageVisible(boolean visible) {
    RootPanel.get(LoginConstant.WRONG_CREDENTIAL_ERROR_MESSAGE).setVisible(visible);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void showLoadingIndicator() {
    JepClientUtil.showLoadingPanel(loginText.login_loadingText_authorizationProcess(), null);
  }

  /**
   * Выполняет запрос на аутентификацию.<br/>
   * Для этого получает DOM-элемент HTML-формы и вызывает
   * у него submit с помощью нативного метода {@link #submit(Element)}.
   */
  @Override
  public void submit() {
    submit(logonFormPanel.getElement());
  }
 
  /**
   * Нативный метод, вызывающий submit на переданном элементе.
   * @param formElement DOM-элемент
   */
  private native void submit(Element formElement) /*-{
    formElement.submit();
  }-*/;

  @Override
  public String getCsrfToken() {
    return RootPanel.get("x_csrf_token_field").getElement().getAttribute("value");
  }
}