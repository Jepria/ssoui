package org.jepria.sso.ssoui.main.client;

import static org.jepria.sso.ssoui.main.client.SsoUiClientConstant.LOGIN_MODULE_ID;
import org.jepria.sso.ssoui.login.client.LoginClientFactoryImpl;
import org.jepria.sso.ssoui.main.client.ui.main.SsoUiMainModulePresenter;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.technology.jep.jepria.client.async.LoadAsyncCallback;
import com.technology.jep.jepria.client.async.LoadPlainClientFactory;
import com.technology.jep.jepria.client.ui.eventbus.main.MainEventBus;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.main.MainClientFactory;
import com.technology.jep.jepria.client.ui.main.MainClientFactoryImpl;
import com.technology.jep.jepria.client.ui.main.MainModulePresenter;
import com.technology.jep.jepria.client.ui.main.MainView;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactory;
import com.technology.jep.jepria.shared.service.JepMainServiceAsync;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;
import org.jepria.sso.ssoui.main.client.ui.main.SsoUiMainViewImpl;
import com.google.gwt.user.client.ui.IsWidget;

public class SsoUiClientFactoryImpl extends MainClientFactoryImpl<MainEventBus, JepMainServiceAsync> {

  public static MainClientFactory<MainEventBus, JepMainServiceAsync> getInstance() {
    if (instance == null) {
      instance = GWT.create(SsoUiClientFactoryImpl.class);
    }
    return instance;
  }

  private SsoUiClientFactoryImpl() {
    super(LOGIN_MODULE_ID);
  }
  
  @Override
  public MainModulePresenter<? extends MainView, MainEventBus, JepMainServiceAsync, ? extends MainClientFactory<MainEventBus, JepMainServiceAsync>> createMainModulePresenter() {
    return new SsoUiMainModulePresenter(this);
  }

  public void getPlainClientFactory(String moduleId, final LoadAsyncCallback<PlainClientFactory<PlainEventBus, JepDataServiceAsync>> callback) {
    if (LOGIN_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {

        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(SsoUiClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + LOGIN_MODULE_ID);
          return LoginClientFactoryImpl.getInstance();
        }
      });
    }
  }

  private static final IsWidget mainView = new SsoUiMainViewImpl();

  @Override
  public IsWidget getMainView() {
    return mainView;
  }
}
