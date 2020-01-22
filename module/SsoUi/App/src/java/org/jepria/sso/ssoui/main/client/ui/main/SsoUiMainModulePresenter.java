package org.jepria.sso.ssoui.main.client.ui.main;
 
import org.jepria.sso.ssoui.main.client.SsoUiClientFactoryImpl;

import com.technology.jep.jepria.client.ui.eventbus.main.MainEventBus;
import com.technology.jep.jepria.client.ui.main.MainModulePresenter;
import com.technology.jep.jepria.client.ui.main.MainView;
import com.technology.jep.jepria.shared.service.JepMainServiceAsync;

public class SsoUiMainModulePresenter 
  extends MainModulePresenter<MainView, MainEventBus, JepMainServiceAsync, SsoUiClientFactoryImpl> {
     
  public SsoUiMainModulePresenter(SsoUiClientFactoryImpl clientFactory) {
    super(clientFactory);
  }
  
  @Override
  protected void loadUserDataAndEnterScope() {
    enterScope(null);
  }
}
