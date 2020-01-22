package org.jepria.sso.ssoui.main.client.ui.main;

import static org.jepria.sso.ssoui.main.client.SsoUiClientConstant.*;

import java.util.ArrayList;
import java.util.List;

import com.technology.jep.jepria.client.ui.main.MainViewImpl;
import com.technology.jep.jepria.client.ui.main.ModuleConfiguration;

public class SsoUiMainViewImpl extends MainViewImpl {

  @Override
  protected List<ModuleConfiguration> getModuleConfigurations() {
    List<ModuleConfiguration> ret = new ArrayList<>();
    ret.add(new ModuleConfiguration(LOGIN_MODULE_ID, SsoUiText.submodule_login_title()));
    return ret;
  }
}
