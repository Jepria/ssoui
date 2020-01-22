package org.jepria.sso.ssoui.login.client;

import static org.jepria.sso.ssoui.main.client.SsoUiClientConstant.LOGIN_MODULE_ID;

import org.jepria.sso.ssoui.login.client.ui.form.plain.LoginModulePresenter;
import org.jepria.sso.ssoui.login.client.ui.form.plain.LoginModuleViewImpl;
import org.jepria.sso.ssoui.login.shared.record.LoginRecordDefinition;
import org.jepria.sso.ssoui.login.shared.service.LoginService;
import org.jepria.sso.ssoui.login.shared.service.LoginServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.technology.jep.jepria.client.ui.JepPresenter;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactory;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactoryImpl;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;

public class LoginClientFactoryImpl extends PlainClientFactoryImpl<PlainEventBus, LoginServiceAsync> {

  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;

  public LoginClientFactoryImpl() {
    super(LoginRecordDefinition.instance);
  }

  public static PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if (instance == null) {
      instance = GWT.create(LoginClientFactoryImpl.class);
    }
    return instance;
  }

  @Override
  public JepPresenter<PlainEventBus, ? extends PlainClientFactory<PlainEventBus, LoginServiceAsync>> createPlainModulePresenter(
      Place place) {
    return new LoginModulePresenter(LOGIN_MODULE_ID, place, this);
  }

  public IsWidget getModuleView() {
    if (moduleView == null) {
      moduleView = new LoginModuleViewImpl();
    }
    return moduleView;
  }

  @Override
  public LoginServiceAsync createService() {
    return (LoginServiceAsync) GWT.create(LoginService.class);
  }
}
