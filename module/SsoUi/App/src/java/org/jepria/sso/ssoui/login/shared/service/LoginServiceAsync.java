package org.jepria.sso.ssoui.login.shared.service;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;

public interface LoginServiceAsync extends JepDataServiceAsync {
  void bindParamsToSession(Map<String, String> params, AsyncCallback<Void> callback); 
}
